package com.example.basic.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = { "players" })
public class Team {
   @Id
   int teamId;
   String teamName;

   @JsonIgnore
   @OneToMany(mappedBy = "team")
   List<Player> players = new ArrayList<>();
}
