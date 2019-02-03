package com.home_projects.imarket.dao.interfaces;

import com.home_projects.imarket.models.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
