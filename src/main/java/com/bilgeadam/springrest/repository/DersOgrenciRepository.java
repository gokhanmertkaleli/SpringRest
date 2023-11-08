package com.bilgeadam.springrest.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bilgeadam.springrest.model.DersOgrenci;

@Repository
public class DersOgrenciRepository
{
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setJdbcTemplate(@Qualifier(value = "bizimjdbctemplate") JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public List<DersOgrenci> getAll()
	{
		return jdbcTemplate.query("select * from \"public\".\"DERS_OGRENCI\" order by \"ID\" asc", BeanPropertyRowMapper.newInstance(DersOgrenci.class));
	}

	public boolean deleteByID(long id)
	{
		String sql = "delete from \"public\".\"DERS_OGRENCI\" where \"ID\" = :ID";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ID", id);
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}

	public DersOgrenci getByID(long id)
	{
		DersOgrenci Dogrenci = null;
		String sql = "select * from \"public\".\"DERS_OGRENCI\" where \"ID\" = :ABUZIDDIN";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ABUZIDDIN", id);
		Dogrenci = namedParameterJdbcTemplate.queryForObject(sql, paramMap, BeanPropertyRowMapper.newInstance(DersOgrenci.class));
	
		return Dogrenci;
	}

	public boolean save(DersOgrenci Dogr)
	{
		String sql = "INSERT INTO \"public\".\"DERS_OGRENCI\"(\"DERS_ID\", \"OGR_ID\", \"DEVAMSIZLIK\", \"NOTE\")VALUES  (:NAME, :OGR_ID,:DEVAMSIZLIK,:NOTE)";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("NAME", Dogr.getDERS_ID());
		paramMap.put("NAME", Dogr.getOGR_ID());
		paramMap.put("NAME", Dogr.getDEVAMSIZLIK());
		paramMap.put("NAME", Dogr.getNOTE());
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}

	public List<DersOgrenci> getAllLike(String name)
	{
		String sql = "select * from \"public\".\"DERS_OGRENCI\" where \"DERS_ID\" LIKE :DERS_ID";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("DERS_ID", "%" + name + "%"); // % işareti parameter içersinde olacak
		return namedParameterJdbcTemplate.query(sql, paramMap, BeanPropertyRowMapper.newInstance(DersOgrenci.class));
	}


}