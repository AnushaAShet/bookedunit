package com.stackroute.service;

import com.stackroute.exceptions.BookedStorageUnitAlreadyExistsException;
import com.stackroute.exceptions.StorageUnitNotFoundException;
import com.stackroute.model.BookedStorageUnit;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface BookedService
{
    public List<BookedStorageUnit> getAllBookedStorageUnit();
    public BookedStorageUnit saveBookedStorageUnit(BookedStorageUnit bookedStorageUnit) throws BookedStorageUnitAlreadyExistsException;
    public boolean deleteBookedStorageUnit(int id) throws StorageUnitNotFoundException;
}

