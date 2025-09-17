package com.callofduty.gamingsystem;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberRepository repo;

    // Create a new member
    @PostMapping
    public Member create(@RequestBody Member member) {
        member.set_id(null);  // let MongoDB generate the _id
        if (member.getDateTime() == null) {
            member.setDateTime(new java.util.Date()); // default now
        }
        return repo.save(member);
    }

    // Get all members
    @GetMapping
    public List<Member> findAll() {
        return repo.findAll();
    }

    // Get member by ID
    @GetMapping("/{id}")
    public Member findById(@PathVariable ObjectId id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Member not found"));
    }

    // Update member by ID
    @PutMapping("/{id}")
    public Member update(@PathVariable ObjectId id, @RequestBody Member member) {
        Member oldMember = repo.findById(id).orElseThrow(() -> new RuntimeException("Member not found"));

        oldMember.setMemberId(member.getMemberId());
        oldMember.setAmount(member.getAmount());
        oldMember.setDateTime(member.getDateTime() != null ? member.getDateTime() : new java.util.Date());

        return repo.save(oldMember);
    }

    // Delete member by ID
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable ObjectId id) {
        Optional<Member> existing = repo.findById(id);
        if (existing.isEmpty()) {
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}
