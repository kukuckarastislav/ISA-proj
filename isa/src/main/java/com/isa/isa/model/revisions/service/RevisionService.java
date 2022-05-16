package com.isa.isa.model.revisions.service;

import com.isa.isa.model.*;
import com.isa.isa.model.revisions.DTO.RevisionAdminViewDTO;
import com.isa.isa.model.termins.model.Revision;
import com.isa.isa.repository.*;
import com.isa.isa.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RevisionService {

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
                Client client = clientRepository.getById(revision.getUserId());     //TODO: vraca null ????
                revisionAdminViewDTOS.add(new RevisionAdminViewDTO(adventure, revision, client));   //TODO: cliente cemo logicki brisati???
            }

        }
        for(Boat boat : boatRepository.findAllWithOwners()) {
            for (Revision revision : boat.getRevisions()) {
                revisionAdminViewDTOS.add(new RevisionAdminViewDTO(boat, revision, clientRepository.getById(revision.getUserId())));
            }
        }
        for(Cottage cottage : cottageRepository.findAllWithOwners()) {
            for (Revision revision : cottage.getRevisions()) {
                revisionAdminViewDTOS.add(new RevisionAdminViewDTO(cottage, revision, clientRepository.getById(revision.getUserId())));
            }
        }
        return revisionAdminViewDTOS;
    }
}
