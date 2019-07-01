package com.stackroute.service;

import com.stackroute.exceptions.BookedStorageUnitAlreadyExistsException;
import com.stackroute.exceptions.StorageUnitNotFoundException;
import com.stackroute.model.BookedStorageUnit;
import com.stackroute.repository.BookedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookedServiceImpl implements BookedService {
    BookedRepository bookedRepository;

    @Autowired
    public BookedServiceImpl(BookedRepository bookedRepository) {
        this.bookedRepository = bookedRepository;
    }

    @Override
    public List<BookedStorageUnit> getAllBookedStorageUnit() {
        return bookedRepository.findAll();
    }

    @Override
    public BookedStorageUnit saveBookedStorageUnit(BookedStorageUnit bookedStorageUnit) throws BookedStorageUnitAlreadyExistsException {


        if (bookedRepository.existsById(bookedStorageUnit.getId())) {
            throw new BookedStorageUnitAlreadyExistsException("warehouse already exists with id:[" + bookedStorageUnit.getId() + "]");
        }
        bookedRepository.save(bookedStorageUnit);
        return bookedStorageUnit;


    }

    @Override
    public boolean deleteBookedStorageUnit(int id) throws StorageUnitNotFoundException {
        boolean status = false;
        if (bookedRepository.existsById(id)) {
            bookedRepository.deleteById(id);
            status = true;
            return status;
        } else {
            throw new StorageUnitNotFoundException("warehouseId not exists");
        }
    }
}

//        @Override
//        public BookedStorageUnit updateStorageUnit(BookedStorageUnit bookedStorageUnit) throws StorageUnitNotFoundException {
//            if(bookedRepository.existsById(bookedStorageUnit.getId()))
//            {
//                BookedStorageUnit savedBookedStorageUnit = bookedRepository.save(bookedStorageUnit);
//                return savedBookedStorageUnit;
//            }
//            else{
//                throw new StorageUnitNotFoundException("wareHouse not found");
//            }
//
//        }
//    }