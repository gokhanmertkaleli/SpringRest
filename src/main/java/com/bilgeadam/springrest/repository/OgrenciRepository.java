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

import com.bilgeadam.springrest.model.Ogrenci;

@Repository
public class OgrenciRepository
{
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setJdbcTemplate(@Qualifier(value = "bizimjdbctemplate") JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public List<Ogrenci> getAll()
	{
		return jdbcTemplate.query("select * from \"public\".\"OGRENCİ\" order by \"ID\" asc", BeanPropertyRowMapper.newInstance(Ogrenci.class));
	}

	public boolean deleteByID(long id)
	{
		String sql = "delete from \"public\".\"OGRENCİ\" where \"ID\" = :ID";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ID", id);
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}

	public Ogrenci getByID(long id)
	{
		Ogrenci ogrenci = null;
		String sql = "select * from \"public\".\"OGRENCİ\" where \"ID\" = :ABUZIDDIN";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ABUZIDDIN", id);
		ogrenci = namedParameterJdbcTemplate.queryForObject(sql, paramMap, BeanPropertyRowMapper.newInstance(Ogrenci.class));
	
		return ogrenci;
	}

	public boolean save(Ogrenci ogr)
	{
		String sql = "insert into \"public\".\"OGRENCİ\" (\"NAME\", \"OGR_NUMBER\",\"YEAR\") values (:NAME, :OGR_NUMBER,:YEAR)";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("NAME", ogr.getNAME());
		paramMap.put("NUMBER", ogr.getOGR_NUMBER());
		paramMap.put("YEAR", ogr.getYEAR());
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}

	public List<Ogrenci> getAllLike(String name)
	{
		String sql = "select * from \"public\".\"OGRETMEN\" where \"NAME\" LIKE :NAME";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("NAME", "%" + name + "%"); // % işareti parameter içersinde olacak
		return namedParameterJdbcTemplate.query(sql, paramMap, BeanPropertyRowMapper.newInstance(Ogrenci.class));
	}


}