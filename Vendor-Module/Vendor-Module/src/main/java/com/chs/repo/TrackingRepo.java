package com.chs.repo;
import com.chs.model.Tracking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrackingRepo extends MongoRepository<Tracking,String> {
}
