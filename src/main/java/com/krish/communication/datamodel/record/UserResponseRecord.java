package com.krish.communication.datamodel.record;

import com.krish.communication.datamodel.Communication;

import java.util.List;

public record UserResponseRecord(String id, String name, String email, String phone, List<Communication> communicationList) {
}
