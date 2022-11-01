package com.requirement.api.mapper;

import com.requirement.api.model.Requirement;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RequirementMapper {
/*
    @Select("select id_requirement,id_worker_applicant,description,attachedFile from requirement")
    @Results(id = "requirementResultMap", value = {
            @Result(property = "id", column = "id_requirement"),
            @Result(property = "idWorkerApplicant", column = "id_worker_applicant"),
            @Result(property = "description", column = "description"),
            @Result(property = "attachedFile", column = "attached_file")
    })
    @ResultMap("requirementResultMap")
    List<Requirement> findAll();*/

    @ResultType(Requirement.class)
    @Select("select id_requirement as id, id_worker_applicant as idWorkerApplicant, description as description, attached_file as AttachedFile from requirement")
    List<Requirement> findAll();

    @Insert("insert into requirement(id_worker_applicant,description,attached_file) values(#{idWorkerApplicant},#{description},#{attachedFile})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id",
            before = false, resultType = Integer.class)
    void insert(Requirement requirement);

    @Update("update requirement set id_worker_applicant=#{idWorkerApplicant}, description=#{description},attached_file=#{attachedFile} where name=#{name}")
    void update(Requirement requirement);

    @Delete("delete from requirement where id_requirement=${id}")
    void delete(@Param("id") Integer id);

    @ResultType(Requirement.class)
    @Select("select id_requirement as id, id_worker_applicant as idWorkerApplicant, description as description, attached_file as AttachedFile from requirement where id_requirement = #{id}")
    Requirement findRequirement(Integer id);
}
