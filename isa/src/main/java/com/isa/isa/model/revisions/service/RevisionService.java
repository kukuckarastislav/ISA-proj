package com.isa.isa.model.revisions.service;

import com.isa.isa.model.*;
import com.isa.isa.model.enums.OwnerType;
import com.isa.isa.model.revisions.DTO.RevisionAdminResponsDTO;
import com.isa.isa.model.revisions.DTO.RevisionAdminViewDTO;
import com.isa.isa.model.revisions.model.RevisionType;
import com.isa.isa.model.revisions.repository.RevisionRepository;
import com.isa.isa.model.revisions.model.Revision;
import com.isa.isa.model.termins.model.StatusOfRevision;
import com.isa.isa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class RevisionService {

    @Autowired
    private RevisionRepository revisionRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private AdventureRepository adventureRepository;

    @Autowired
    private BoatOwnerRepository boatOwnerRepository;

    @Autowired
    private BoatRepository boatRepository;

    @Autowired
    private CottageOwnerRepository cottageOwnerRepository;

    @Autowired
    private CottageRepository cottageRepository;


    @Autowired
    private ClientRepository clientRepository;


    public ArrayList<RevisionAdminViewDTO> getRevisionAdminView() {
        ArrayList<RevisionAdminViewDTO> revisionAdminViewDTOS = new ArrayList<RevisionAdminViewDTO>();

        for(Adventure adventure : adventureRepository.findAllWithInstructors()) {
            for (Revision revision : adventure.getRevisions()) {
                Client client = clientRepository.findById(revision.getUserId()).get();
                revisionAdminViewDTOS.add(new RevisionAdminViewDTO(RevisionType.ENTITY, adventure.getName(), adventure.getInstructor().getEmail(), OwnerType.INSTRUCTOR, client.getEmail(), revision));   //TODO: cliente cemo logicki brisati???
            }

        }
        for(Boat boat : boatRepository.findAllWithOwners()) {
            for (Revision revision : boat.getRevisions()) {
                Client client = clientRepository.findById(revision.getUserId()).get();
                revisionAdminViewDTOS.add(new RevisionAdminViewDTO(RevisionType.ENTITY, boat.getName(), boat.getOwner().getEmail(), OwnerType.BOAT_OWNER,client.getEmail(), revision));
            }
        }
        for(Cottage cottage : cottageRepository.findAllWithOwners()) {
            for (Revision revision : cottage.getRevisions()) {
                Client client = clientRepository.findById(revision.getUserId()).get();
                revisionAdminViewDTOS.add(new RevisionAdminViewDTO(RevisionType.ENTITY, cottage.getName(), cottage.getOwner().getEmail(), OwnerType.COTTAGE_OWNER, client.getEmail(), revision));
            }
        }
        return revisionAdminViewDTOS;
    }

    public ArrayList<RevisionAdminViewDTO> getRevisionOwnersAdminView() {
        ArrayList<RevisionAdminViewDTO> revisionAdminViewDTOS = new ArrayList<RevisionAdminViewDTO>();

        for(Instructor instructor : instructorRepository.findAll()) {
            for (Revision revision : instructor.getRevisions()) {
                Client client = clientRepository.findById(revision.getUserId()).get();
                revisionAdminViewDTOS.add(new RevisionAdminViewDTO(RevisionType.OWNER, "", instructor.getEmail(), OwnerType.INSTRUCTOR, client.getEmail(), revision));   //TODO: cliente cemo logicki brisati???
            }
        }
        for(BoatOwner boatOwner : boatOwnerRepository.findAll()) {
            for (Revision revision : boatOwner.getRevisions()) {
                Client client = clientRepository.findById(revision.getUserId()).get();
                revisionAdminViewDTOS.add(new RevisionAdminViewDTO(RevisionType.OWNER, "", boatOwner.getEmail(), OwnerType.BOAT_OWNER, client.getEmail(), revision));
            }
        }
        for(CottageOwner cottageOwner : cottageOwnerRepository.findAll()) {
            for (Revision revision : cottageOwner.getRevisions()) {
                Client client = clientRepository.findById(revision.getUserId()).get();
                revisionAdminViewDTOS.add(new RevisionAdminViewDTO(RevisionType.OWNER, "",cottageOwner.getEmail(), OwnerType.COTTAGE_OWNER, client.getEmail(), revision));
            }
        }

        return revisionAdminViewDTOS;
    }

    public Boolean postAdminRevisionRespons(RevisionAdminResponsDTO revisionAdminResponsDTO) {
        Optional<Revision> revision = revisionRepository.findById(revisionAdminResponsDTO.getIdRevision());
        if(revision.isPresent()){
            Revision rev = revision.get();

            if(rev.getStatusOfRevision() != StatusOfRevision.PENDING)
                return false;

            rev.setAdminResponsDate(LocalDateTime.now());
            rev.setAdminUsername(revisionAdminResponsDTO.getAdminUsername());
            if(revisionAdminResponsDTO.isApprove()){
                rev.setStatusOfRevision(StatusOfRevision.APPROVED);
                callculateNewGrade(rev, revisionAdminResponsDTO);
                // poslati email pruzaocu usluge
            }else{
                rev.setStatusOfRevision(StatusOfRevision.REJECTED);
            }

            revisionRepository.saveAndFlush(rev);
            return true;
        }

        return false;
    }

    private Boolean callculateNewGrade(Revision revision, RevisionAdminResponsDTO revisionAdminResponsDTO){
        if(revisionAdminResponsDTO.getRevisionType() == RevisionType.ENTITY){
            if(revisionAdminResponsDTO.getOwnerType() == OwnerType.INSTRUCTOR){
                Adventure adventure = adventureRepository.getAdventureByRevisionId(revision.getId());
                if(adventure != null){
                    adventure.callculateGrade();
                    adventureRepository.saveAndFlush(adventure);
                    return true;
                }
            }else if(revisionAdminResponsDTO.getOwnerType() == OwnerType.BOAT_OWNER){
                Boat boat = boatRepository.getBoatByRevisionId(revision.getId());
                if(boat != null){
                    boat.callculateGrade();
                    boatRepository.saveAndFlush(boat);
                    return true;
                }
            }else if(revisionAdminResponsDTO.getOwnerType() == OwnerType.COTTAGE_OWNER){
                Cottage cottage = cottageRepository.getCottageByRevisionId(revision.getId());
                if(cottage != null){
                    cottage.callculateGrade();
                    cottageRepository.saveAndFlush(cottage);
                    return true;
                }
            }
        }else if(revisionAdminResponsDTO.getRevisionType() == RevisionType.OWNER){
            if(revisionAdminResponsDTO.getOwnerType() == OwnerType.INSTRUCTOR){
                Instructor instructor = instructorRepository.getInstructorByRevisionId(revision.getId());
                if(instructor != null){
                    instructor.callculateGrade();
                    instructorRepository.saveAndFlush(instructor);
                    return true;
                }
            }else if(revisionAdminResponsDTO.getOwnerType() == OwnerType.BOAT_OWNER){
                BoatOwner boatOwner = boatOwnerRepository.getBoatOwnerByRevisionId(revision.getId());
                if(boatOwner != null){
                    boatOwner.callculateGrade();
                    boatOwnerRepository.saveAndFlush(boatOwner);
                    return true;
                }
            }else if(revisionAdminResponsDTO.getOwnerType() == OwnerType.COTTAGE_OWNER){
                CottageOwner cottageOwner = cottageOwnerRepository.getCottageOwnerByRevisionId(revision.getId());
                if(cottageOwner != null){
                    cottageOwner.callculateGrade();
                    cottageOwnerRepository.saveAndFlush(cottageOwner);
                    return true;
                }
            }
        }

        return false;
    }

}
