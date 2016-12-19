package com.xiaomi.gateway.Repository;

import com.xiaomi.gateway.entity.mongo.Device;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Anton Afanasyeu on 12/19/16.
 */
@Repository
public interface DeviceRepository extends MongoRepository<Device, String> {
    List<Device> findById(@Param("id") String id);
}
