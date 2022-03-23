package com.example.springsecuritysystem.repository;


import com.example.springsecuritysystem.entity.system.user.Permission;
import com.example.springsecuritysystem.entity.system.user.Role;
import com.example.springsecuritysystem.projection.PermissionIdAndName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Set;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    List<Permission> findAllByIdIn(Set<Integer> ids);

    List<Permission> findAllByRolesIn(List<Role> roles);

    @Query(nativeQuery = true, value = "select (per.id) id, (per.name) name from permission per order by per.id")
    List<PermissionIdAndName> findAllPermissionIdAndName();

    @Query(nativeQuery = true, value = "select (per.id) id, (per.name) name from permission per where id not in (select rp.permissions_id from role_permissions rp where rp.roles_id in ?1) order by per.id")
    List<PermissionIdAndName> findAllPermissionIdAndNameByRolesNotIn(List<Integer> roles);
}
