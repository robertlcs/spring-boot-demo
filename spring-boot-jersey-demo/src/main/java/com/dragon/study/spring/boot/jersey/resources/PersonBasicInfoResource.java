package com.dragon.study.spring.boot.jersey.resources;

import com.google.common.base.Strings;

import com.dragon.study.spring.boot.hibernate.module.PersonBasicInfo;
import com.dragon.study.spring.boot.jersey.exception.PersonBasicInfoException;
import com.dragon.study.spring.boot.jersey.module.CommonResponse;
import com.dragon.study.spring.boot.jersey.service.IPersonBasicInfoService;

import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by dragon on 16/7/29.
 */
@Path("/spring-boot/person")
@Slf4j
public class PersonBasicInfoResource {

  @Autowired
  IPersonBasicInfoService personBasicInfoService;

  @Path("/register")
  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  public CommonResponse register(
      @BeanParam
      PersonBasicInfo personBasicInfo,
      @FormParam("country")
      String country) {
    if (Strings.isNullOrEmpty(personBasicInfo.getPhone())) {
      log.error("phone is empty or null");
      throw new PersonBasicInfoException(
          PersonBasicInfoException.BasicInfoExceptionFactor.NO_PHONE_FAILURE);
    }
    personBasicInfoService.registerPerson(personBasicInfo, country);
    return CommonResponse.of(true);
  }

  //TODO curl 'http://127.0.0.1:8088/jersey/spring-boot/person/queryPerson/18507313226'
  @Path("/queryPerson/{phone}")
  @GET
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  public PersonBasicInfo queryPerson(
      @PathParam("phone")
      String phone) {
    if (Strings.isNullOrEmpty(phone)) {
      log.error("phone is empty or null");
      throw new PersonBasicInfoException(
          PersonBasicInfoException.BasicInfoExceptionFactor.NO_PHONE_FAILURE);
    }
    return personBasicInfoService.queryPersonBasicInfo(phone);
  }

}
