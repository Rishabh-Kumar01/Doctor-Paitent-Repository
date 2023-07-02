package com.hospital.controllers;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.dto.DoctorDto;
import com.hospital.dto.ReceptionistDto;
import com.hospital.entities.Appointment;
import com.hospital.entities.Doctor;
import com.hospital.entities.Patient;
import com.hospital.entities.Receptionist;
import com.hospital.entities.User;
import com.hospital.security.AuthenticationRequest;
import com.hospital.security.AuthenticationResponse;
import com.hospital.security.CustomUserDetailsService;
import com.hospital.security.JwtUtil;
import com.hospital.services.AppointmentService;
import com.hospital.services.DoctorService;
import com.hospital.services.PatientService;
import com.hospital.services.ReceptionistService;
import com.hospital.services.UserService;

@RestController
@RequestMapping("admin")
public class AdminController {
	
	@Autowired private DoctorService doctorService;
	
	@Autowired private UserService userService;
	
	@Autowired private AppointmentService appointmentService;
	
	@Autowired private PatientService patientService;
	
	@Autowired private ReceptionistService receptionistService;
	
	@Autowired private ModelMapper modelMapper;

	@Autowired private AuthenticationManager authenticatioManager;
	
	@Autowired private CustomUserDetailsService userDetailsService;
	
	@Autowired private JwtUtil jwtUtil;
	
//	@Autowired
//    private CsrfTokenRepository csrfTokenRepository;
//	
	
	@PostMapping("authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			authenticatioManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect Username and Password", e);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	/*
	 * Add Doctor - POST 
	 */
	@PostMapping("doctor/add")
	public ResponseEntity<DoctorDto> createDoctor(@Valid @RequestBody DoctorDto doctorDto){
		Doctor doctor = modelMapper.map(doctorDto, Doctor.class);
		doctorService.saveDoctor(doctor);
		User user = modelMapper.map(doctorDto, User.class);
		user.setUsername(doctorDto.getEmailid());
		userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(doctorDto);
	}
	
//	@PostMapping("doctor/add")
//	public ResponseEntity<DoctorDto> createDoctor(@Valid @RequestBody DoctorDto doctorDto){
//		Doctor doctor = modelMapper.map(doctorDto, Doctor.class);
//        doctorService.saveDoctor(doctor);
//        User user = modelMapper.map(doctorDto, User.class);
//        user.setUsername(doctorDto.getEmailid());
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("X-CSRF-Token", "5075b3ef-0f4f-4bd1-9ad1-ad29681918e4");
//        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
//        userService.saveUser(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(doctorDto);
//	}
	
//	@PostMapping("doctor/add")
//	public ResponseEntity<DoctorDto> createDoctor(@Valid @RequestBody DoctorDto doctorDto,
//	                                              CsrfToken token) {
//	    // Generate CSRF token
//	    HttpHeaders headers = new HttpHeaders();
//	    headers.set("X-CSRF-Token", token.getToken());
//	    HttpEntity<DoctorDto> entity = new HttpEntity<>(doctorDto, headers);
//
//	    // Save the doctor
//	    Doctor doctor = modelMapper.map(doctorDto, Doctor.class);
//	    doctorService.saveDoctor(doctor);
//
//	    // Save the user
//	    User user = modelMapper.map(doctorDto, User.class);
//	    user.setUsername(doctorDto.getEmailid());
//	    userService.saveUser(user);
//
//	    // Return the response
//	    return ResponseEntity.status(HttpStatus.CREATED).body(doctorDto);
//	}

	
//	@PostMapping("doctor/add")
//	public ResponseEntity<DoctorDto> createDoctor(@Valid @RequestBody DoctorDto doctorDto, HttpServletRequest request) {
//	    Doctor doctor = modelMapper.map(doctorDto, Doctor.class);
//	    doctorService.saveDoctor(doctor);
//	    User user = modelMapper.map(doctorDto, User.class);
//	    user.setUsername(doctorDto.getEmailid());
//	    userService.saveUser(user);
//	    
//	    // Retrieve the XSRF token value from the cookie
//	    Cookie[] cookies = request.getCookies();
//	    String xsrfToken = null;
//	    if (cookies != null) {
//	        for (Cookie cookie : cookies) {
//	            if (cookie.getName().equals("XSRF-TOKEN")) {
//	                xsrfToken = cookie.getValue();
//	                break;
//	            }
//	        }
//	    }
//	    
//	    // Include the XSRF token in the request headers
//	    HttpHeaders headers = new HttpHeaders();
//	    headers.set("X-XSRF-TOKEN", xsrfToken);
//	    HttpEntity<String> requestEntity = new HttpEntity<>(headers);
//	    
//	    // Make the request to the user service using RestTemplate or any HTTP client library you are using
//	    // Replace the URL and HttpMethod with your appropriate endpoint
//	    ResponseEntity<DoctorDto> responseEntity = restTemplate.exchange(
//	    	    "http://localhost:8080/user/save",
//	    	    HttpMethod.POST,
//	    	    requestEntity,
//	    	    DoctorDto.class
//	    	);
//	    
//	    // Handle the response from the user service
//	    
//	    return ResponseEntity.status(HttpStatus.CREATED).body(doctorDto);
//	}
	



	
	/*
	 * Lists of Doctor - GET
	 */
	@GetMapping("doctor/list")
	public ResponseEntity<List<Doctor>> getDoctorsList(){
		List<Doctor> doctors = doctorService.doctorsList();
		return ResponseEntity.ok(doctors);
	}
	
	/*
	 * Delete Doctor - DELETE
	 */
	@DeleteMapping("doctor/delete")
	public ResponseEntity<String> deleteDoctor(@RequestParam String email){
		Doctor doctor = doctorService.getDoctor(email);
		doctorService.deleteDoctor(doctor);
		userService.deleteUser(doctor.getEmailid());
		return ResponseEntity.ok("Doctor and User deleted successfully");
	}
	
	/*
	 * Update Doctor - PATCH 
	 */
	@PatchMapping("doctor/update")
	public ResponseEntity<String> updateDoctorDetails(@RequestParam String email, @RequestBody DoctorDto doctorDto) {
		Doctor doctor = doctorService.getDoctor(email);
        doctorService.updateDoctorDetails(doctor , doctorDto);
        User user = userService.getUser(email);
        userService.updateUserDetails(user, doctorDto);
        return ResponseEntity.ok("Doctor details updated successfully.");
    }
	
	/*
	 * Create Appointment - POST 
	 */
	@PostMapping("appointment/add")
	public ResponseEntity<Appointment> createAppointment(@Valid @RequestBody Appointment appointment){
		appointmentService.saveAppointment(appointment);
		return ResponseEntity.ok(appointment);
	}
	
	/*
	 * Delete Appointment - DELETE
	 */
	@DeleteMapping("appointment/delete")
	public ResponseEntity<String> deleteAppointment(@RequestParam String appointmentId){
		Appointment appointment = appointmentService.getAppointment(appointmentId);
		appointmentService.deleteAppointment(appointment);
		return ResponseEntity.ok("Appointment for the patient - "+ appointment.getPatient().getFirstName() + appointment.getPatient().getLastName() + "have been cancelled");
	}
	
	/*
	 * Update Appointment - PATCH 
	 */
	@PatchMapping("appointment/update")
	public ResponseEntity<String> updateAppointment(@RequestParam String appointmentId, @RequestBody Appointment appointment){
		Appointment updateAppointment = appointmentService.getAppointment(appointmentId);
		appointmentService.updateAppointment(appointment, updateAppointment);
		return ResponseEntity.ok("Appointment details updated successfully");
	}
	
	/*
	 * Create Patient - POST 
	 */
//	@PostMapping("patient/add")
//	public ResponseEntity<Patient> createPatient(@Valid @RequestBody Patient patient){
//		patientService.savePatient(patient);
//		return ResponseEntity.ok(patient);
//	}
	
	/*
	 * Delete Patient - DELETE
	 */
	@DeleteMapping("patient/delete")
	public ResponseEntity<String> deletePatient(@RequestParam("patientId") String patientId){
		Patient patient = patientService.getPatient(patientId);
		appointmentService.checkAppointmentStatus(patient);
		patientService.deletePatient(patient);
		return ResponseEntity.ok("Patient with Id " + patientId + " have been deleted");
	}
	
	/*
	 * Update Patient - PATCH 
	 */
	@PatchMapping("patient/update")
	public ResponseEntity<String> updatePatient(@RequestParam("patientId") String patientId, @RequestBody Patient patient){
		Patient updatePatient = patientService.getPatient(patientId);
		patientService.updatePatient(patient, updatePatient);
		return ResponseEntity.ok("Patient details update successfully");
	}
	
	/*
	 * Create Receptionist - POST 
	 */
	@PostMapping("receptionist/add")
	public ResponseEntity<Receptionist> createReceptionist(@Valid @RequestBody ReceptionistDto receptionistDto){
		Receptionist receptionist = modelMapper.map(receptionistDto, Receptionist.class);
		receptionistService.saveReceptionist(receptionist);
		User user = modelMapper.map(receptionistDto, User.class);
		user.setUsername(receptionistDto.getEmailId());
		userService.saveUser(user);
		return ResponseEntity.ok(receptionist);
	}
	
	/*
	 * Lists of Receptionist - GET
	 */
	@GetMapping("receptionist/list")
	public ResponseEntity<List<Receptionist>> getReceptionistList(){
		List<Receptionist> receptionists = receptionistService.receptionistList();
		return ResponseEntity.ok(receptionists);
	}
	

	/*
	 * Delete Receptionist - DELETE
	 */
	@DeleteMapping("receptionist/delete")
	public ResponseEntity<String> deleteReceptionist(@RequestParam String email){
		Receptionist receptionist = receptionistService.getReceptionist(email);
		receptionistService.deleteReceptionist(receptionist);
		return ResponseEntity.ok("Receptionist Deleted Successfully");
	}
	
	
	/*
	 * Update Receptionist - UPDATE
	 */
	@PatchMapping("receptionist/update")
	public ResponseEntity<String> updateReceptionist(@RequestParam String emailId, @RequestBody ReceptionistDto receptionistDto){
		Receptionist receptionist = receptionistService.getReceptionist(emailId);
		receptionistService.updateReceptionist(receptionist, receptionistDto);
		User user = userService.getUser(emailId);
		userService.updateUserDetailsOfReceptionist(user, receptionistDto);
		return ResponseEntity.ok("Receptionist details update successfully");
	}
	
}
