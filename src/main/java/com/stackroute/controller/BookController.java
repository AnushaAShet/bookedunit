package com.stackroute.controller;
import com.stackroute.exceptions.BookedStorageUnitAlreadyExistsException;
import com.stackroute.exceptions.StorageUnitNotFoundException;
import com.stackroute.model.BookedStorageUnit;
import com.stackroute.service.BookedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value="api/v1")
public class BookController
{
        BookedService bookedService;
        @Autowired
        public BookController(BookedService bookedService) {
            this.bookedService = bookedService;
        }

    @PostMapping("/savebookings")
    public ResponseEntity<?> addBookedStorageUnit(@RequestBody BookedStorageUnit bookedStorageUnit)  throws BookedStorageUnitAlreadyExistsException {
        ResponseEntity responseEntity;
        try{
            bookedService.saveBookedStorageUnit(bookedStorageUnit);
            responseEntity=new ResponseEntity<String>("storage space Successfully created", HttpStatus.CREATED);

        }
        catch (BookedStorageUnitAlreadyExistsException ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);

        }
        return responseEntity;
    }
        @GetMapping("/getallbookings")
        public ResponseEntity<?> getAllBookedStorageUnit(){
            return  new ResponseEntity<List<BookedStorageUnit>>(bookedService.getAllBookedStorageUnit(),HttpStatus.OK);
        }

//        @PatchMapping("/patchbookings")
//        public ResponseEntity<?> updatebookedStorage(@RequestBody bookedStorageUnit listedStorageUnit) throws StorageUnitNotFound {
//            ResponseEntity responseEntity;
//            try{
//                listedService.updateStorageUnit(listedStorageUnit);
//                responseEntity=new ResponseEntity<String>("storagespace Updated Successfully", HttpStatus.CREATED);
//            }
//            catch(StorageUnitNotFound exception){
//                responseEntity=new ResponseEntity<String>(exception.getMessage(),HttpStatus.CONFLICT);
//            }
//            return responseEntity;
//        }

        @DeleteMapping("/storageNo/{id}")
        public ResponseEntity<?> deleteBookedStorage(@PathVariable("id") Integer id) throws StorageUnitNotFoundException
        {
            ResponseEntity responseEntity;
            try {
                bookedService.deleteBookedStorageUnit(id);
                responseEntity = new ResponseEntity<String>("storagespace Deleted successfully", HttpStatus.OK);
            }
            catch(StorageUnitNotFoundException exception){

                responseEntity=new ResponseEntity<String>(exception.getMessage(),HttpStatus.CONFLICT);
            }
            return responseEntity;
        }
    }
