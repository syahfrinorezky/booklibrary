package com.example.booklibrary.Controller;

import java.time.LocalDateTime;
import java.util.List;

import com.example.booklibrary.Model.Members;
import com.example.booklibrary.Repo.MemberRepo;
import com.example.booklibrary.Response.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class MemberController {
    @Autowired
    private MemberRepo memberRepo;

    @GetMapping("members")
    public ResponseEntity<ApiResponse> getAllMembers() {
        List<Members> members = memberRepo.findAll();

        return ResponseEntity.ok(
                new ApiResponse("success", "Members retrieved successfully", members));
    }

    @GetMapping("members/{id}")
    public ResponseEntity<ApiResponse> getMemberById(@PathVariable Long id) {
        Members member = memberRepo.findById(id).orElse(null);

        if (member == null) {
            return ResponseEntity.status(404).body(
                    new ApiResponse("failed", "Member not found", null));
        }

        return ResponseEntity.ok(
                new ApiResponse("success", "Member retrieved successfully", member));
    }

    @PostMapping("members")
    public ResponseEntity<ApiResponse> addMember(@RequestBody Members member) {
        Members newMember = memberRepo.save(member);

        return ResponseEntity.status(201).body(
                new ApiResponse("success", "Member added successfully", newMember));
    }

    @PutMapping("members/{id}")
    public ResponseEntity<ApiResponse> updateMember(@PathVariable Long id, @RequestBody Members newData) {
        Members existingMember = memberRepo.findById(id).orElse(null);

        if (existingMember == null) {
            return ResponseEntity.status(404).body(
                    new ApiResponse("failed", "Member not found", null));
        }

        existingMember.setName(newData.getName());
        existingMember.setPhone(newData.getPhone());
        existingMember.setAddress(newData.getAddress());

        Members updatedMember = memberRepo.save(existingMember);

        return ResponseEntity.ok(
                new ApiResponse(
                        "success", "Member updated successfully", updatedMember));
    }

    @DeleteMapping("members/{id}")
    public ResponseEntity<ApiResponse> deleteMember(@PathVariable Long id) {
        Members existingMember = memberRepo.findById(id).orElse(null);

        if (existingMember == null) {
            return ResponseEntity.status(404).body(
                    new ApiResponse("failed", "Member not found", null));
        }

        existingMember.setDeletedAt(LocalDateTime.now());
        memberRepo.save(existingMember);

        return ResponseEntity.ok(
                new ApiResponse("success", "Member deleted successfully", null));
    }
}
