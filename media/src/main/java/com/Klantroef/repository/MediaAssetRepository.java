package com.Klantroef.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Klantroef.model.MediaAsset;

@Repository
public interface MediaAssetRepository extends JpaRepository<MediaAsset, Long> {
}