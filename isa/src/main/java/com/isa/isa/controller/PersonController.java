package com.isa.isa.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.isa.DTO.UserDTO;
import com.isa.isa.model.Adventure;
import com.isa.isa.model.Boat;
import com.isa.isa.model.BoatOwner;
import com.isa.isa.model.Cottage;
import com.isa.isa.model.CottageOwner;
import com.isa.isa.model.Instructor;
import com.isa.isa.model.report.model.Report;
import com.isa.isa.model.termins.DTO.BoatTermsDTO;
import com.isa.isa.model.termins.DTO.ClientAdventureFastReservationDTO;
import com.isa.isa.model.termins.DTO.ClientBoatFastReservationDTO;
import com.isa.isa.model.termins.DTO.ClientCottageFastReservationDTO;
import com.isa.isa.model.termins.DTO.CottageTermsDTO;
import com.isa.isa.model.termins.DTO.InstructorTermsDTO;
import com.isa.isa.model.termins.model.BoatFastReservation;
import com.isa.isa.model.termins.model.CottageFastReservation;
import com.isa.isa.model.termins.service.BoatFastReservationService;
import com.isa.isa.model.termins.service.CottageFastReservationService;
import com.isa.isa.model.termins.service.InstructorFastReservationService;
import com.isa.isa.service.AdventureService;
import com.isa.isa.service.BoatOwnerService;
import com.isa.isa.service.BoatService;
import com.isa.isa.service.ClientService;
import com.isa.isa.service.CottageOwnerService;
import com.isa.isa.service.CottageService;
import com.isa.isa.service.InstructorService;
import com.isa.isa.service.PenaltyService;


@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private InstructorService instructorService;
    @Autowired
    private CottageOwnerService cottageOwnerService;
    @Autowired
    private BoatOwnerService boatOwnerService;
    @Autowired
    private CottageService cottageService;
    @Autowired
    private BoatService boatService;
    @Autowired
    private AdventureService adventureService;
    @Autowired
    private InstructorFastReservationService instructorFastReservationService;
    @Autowired
    private CottageFastReservationService cottageFastReservationService;
    @Autowired
    private BoatFastReservationService boatFastReservationService;
    
    @Autowired 
    private PenaltyService penaltyService;
    @Autowired
    private ClientService clientService;
    
    
    @PutMapping("/registration")
    public ResponseEntity<UserDTO> registrationStuff(@RequestBody UserDTO userDTO){

        System.out.println("[api/person/registration] stuffDTO: " + userDTO.toString());

        if(userDTO.isAdmin()){

        }else if(userDTO.isCottageOwner()){
        	
        	CottageOwner owner = cottageOwnerService.register(userDTO);
        	if(owner==null)
        		return new ResponseEntity(userDTO,HttpStatus.BAD_REQUEST);//promeniti da vraca userexists

        }else if(userDTO.isBoatOwner()){
        	
        	BoatOwner owner = boatOwnerService.register(userDTO);
        	if(owner==null)
        		return new ResponseEntity(userDTO,HttpStatus.BAD_REQUEST);//promeniti da vraca userexists


        }else if(userDTO.isInstructor()){
            System.out.println("Registracija instruktora");
            instructorService.save(userDTO);
            System.out.println("Registracija uspesna");
        }else{
            return new ResponseEntity(userDTO, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
    }
    
    @GetMapping("/instructors")
    public ResponseEntity<ArrayList<Instructor>> getAllInstructorsWithAdventures() {
        ArrayList<Instructor> instructors = instructorService.getAllInstructorsWithAdventures();
        return new ResponseEntity<ArrayList<Instructor>>(instructors, HttpStatus.OK);
    }
    
    @GetMapping("/instructors/{instructorId}")
    public ResponseEntity<Instructor> getAdventureByInstructorAndName(@PathVariable Integer instructorId) {
        return new ResponseEntity<Instructor>(instructorService.getInstructorById(instructorId), HttpStatus.OK);
    }
    
    @GetMapping("/cottages")
    public ResponseEntity<ArrayList<Cottage>> getAllCottages() {
        return new ResponseEntity<ArrayList<Cottage>>(cottageService.getAllCottages(), HttpStatus.OK);
    }
    
    @GetMapping("/cottages/{cottageId}")
    public ResponseEntity<Cottage> getCottageById(@PathVariable Integer cottageId) {
        return new ResponseEntity<Cottage>(cottageService.getCottageWithOwner(cottageId), HttpStatus.OK);
    }
    
    @GetMapping("/boats")
    public ResponseEntity<ArrayList<Boat>> getAllBoats() {
        return  new ResponseEntity<ArrayList<Boat>>(boatService.getAllBoats(), HttpStatus.OK);
    }
    
    @GetMapping("/boats/{boatId}")
    public ResponseEntity<Boat> getBoatById(@PathVariable Integer boatId) {
        return new ResponseEntity<Boat>(boatService.getBoatWithOwner(boatId), HttpStatus.OK);
    }
    
    @GetMapping("/adventures")
    public ResponseEntity<ArrayList<Adventure>> getAllAdventures() {
        return new ResponseEntity<ArrayList<Adventure>>(adventureService.getAllAdventures(), HttpStatus.OK);
    }
    
    @GetMapping("/adventures/{adventureId}")
    public ResponseEntity<Adventure> getAdventureById(@PathVariable Integer adventureId) {
        return new ResponseEntity<Adventure>(adventureService.getAdventureWithInstructor(adventureId), HttpStatus.OK);
    }
    
    @PostMapping("/adventures/instructorFree")
    public ResponseEntity<Boolean> checkIfInsturctorFree(@RequestBody InstructorTermsDTO termDTO){

    	return new ResponseEntity<Boolean>(instructorService.isInstructorFree(termDTO), HttpStatus.OK);
    }
    
    @GetMapping("/adventures/{adventureId}/fastReservations")
    public ResponseEntity<ArrayList<ClientAdventureFastReservationDTO>> getAdventureFastReservationsById(@PathVariable Integer adventureId) {
    	ArrayList<ClientAdventureFastReservationDTO> instructorFastReservations = (ArrayList<ClientAdventureFastReservationDTO>) instructorFastReservationService.getFastReservationsByAdventure(adventureId);
        return new ResponseEntity<ArrayList<ClientAdventureFastReservationDTO>>(instructorFastReservations, HttpStatus.OK);
    }
    
    @PostMapping("/cottages/cottageFree")
    public ResponseEntity<Boolean> checkIfCottageFree(@RequestBody CottageTermsDTO cottageTermsDTO){

    	return new ResponseEntity<Boolean>(cottageService.isCottageFree(cottageTermsDTO), HttpStatus.OK);
    }
    
    @GetMapping("/cottages/{cottageId}/fastReservations")
    public ResponseEntity<ArrayList<ClientCottageFastReservationDTO>> getCottageFastReservationsById(@PathVariable Integer cottageId) {
    	ArrayList<ClientCottageFastReservationDTO> clientFastReservations = (ArrayList<ClientCottageFastReservationDTO>) cottageFastReservationService.getFastReservationsByCottage(cottageId);
        return new ResponseEntity<ArrayList<ClientCottageFastReservationDTO>>(clientFastReservations, HttpStatus.OK);
    }
    
    @PostMapping("/boats/boatFree")
    public ResponseEntity<Boolean> checkIfBoatFree(@RequestBody BoatTermsDTO boatTermsDTO){

    	return new ResponseEntity<Boolean>(boatService.isBoatFree(boatTermsDTO), HttpStatus.OK);
    }
    
    @PutMapping("/boats/ownerPresence") 
    public ResponseEntity<Boat> checkIfBoatOwnersFree(@RequestBody BoatTermsDTO boatTermsDTO) {
      Boat boat =  boatService.getBoatWithOwner(boatTermsDTO.getId());
      boat = boatService.isOwnerFree(boatTermsDTO, boat);
      return new ResponseEntity<Boat>(boat, HttpStatus.OK);
    }
    
    @GetMapping("/boats/{boatId}/fastReservations")
    public ResponseEntity<ArrayList<ClientBoatFastReservationDTO>> getBoatFastReservationsById(@PathVariable Integer boatId) {
    	ArrayList<ClientBoatFastReservationDTO> clientFastReservations = (ArrayList<ClientBoatFastReservationDTO>) boatFastReservationService.getFastReservationsByBoat(boatId);
        return new ResponseEntity<ArrayList<ClientBoatFastReservationDTO>>(clientFastReservations, HttpStatus.OK);
    }
    
    @GetMapping("/test")
    public ResponseEntity<?> test() {
    	Report report = new Report();
    	report.setClientShowedUp(true);
    	report.setIdClient(3);
    	report.setSanctionClient(true);
    	report.setText("Ostavio stan u haosu.");
    	penaltyService.getPenaltyFromReport(report);
    	return ResponseEntity.ok().build();
    }
    
    @GetMapping("/testCottageAction")
    public ResponseEntity<?> testCottageAction() {
    	CottageFastReservation cottageFastReservation = new CottageFastReservation();
    	Cottage cottage = new Cottage();
    	cottage.setId(1);
    	cottage.setName("Stan");
    	cottageFastReservation.setCottage(cottage);
    	cottageFastReservation.setStartTime(LocalDateTime.now());
    	cottageFastReservation.setEndTime(LocalDateTime.now().plusDays(2));
    	clientService.notifyCottageSubscribedClients(cottageFastReservation);
    	return ResponseEntity.ok().build();
    }
    
    @GetMapping("/testBoatAction")
    public ResponseEntity<?> testBoatAction() {
    	BoatFastReservation boatFastReservation = new BoatFastReservation();
    	Boat boat = new Boat();
    	boat.setId(2);
    	boat.setName("Serenity");
    	boatFastReservation.setBoat(boat);
    	boatFastReservation.setStartTime(LocalDateTime.now());
    	boatFastReservation.setEndTime(LocalDateTime.now().plusHours(2));
    	clientService.notifyBoatSubscribedClients(boatFastReservation);
    	return ResponseEntity.ok().build();
    }
    
}
