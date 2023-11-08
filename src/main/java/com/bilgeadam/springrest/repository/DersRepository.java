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

import com.bilgeadam.springrest.model.Ders;

@Repository
public class DersRepository
{
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setJdbcTemplate(@Qualifier(value = "bizimjdbctemplate") JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public List<Ders> getAll()
	{
		return jdbcTemplate.query("select * from \"public\".\"DERS\" order by \"ID\" asc", BeanPropertyRowMapper.newInstance(Ders.class));
	}

	public boolean deleteByID(long id)
	{
		String sql = "delete from \"public\".\"DERS\" where \"ID\" = :ID";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ID", id);
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}

	public Ders getByID(long id)
	{
		Ders ders = null;
		String sql = "select * from \"public\".\"DERS\" where \"ID\" = :ABUZIDDIN";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ABUZIDDIN", id);
		ders = namedParameterJdbcTemplate.queryForObject(sql, paramMap, BeanPropertyRowMapper.newInstance(Ders.class));
	
		return ders;
	}

	public boolean save(Ders ders)
	{
		String sql = "INSERT INTO \"public\".\"DERS\"(\"OGRETMEN_ID\", \"KONU_ID\")VALUES(:OGRETMEN_ID, :KONU_ID";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("NAME", ders.getOGRETMEN_ID());
		paramMap.put("NAME", ders.getKONU_ID());
		
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}

	public List<Ders> getAllLike(String name)
	{
		String sql = "select * from \"public\".\"DERS\" where \"OGRETMEN_ID\" LIKE :OGRETMEN_ID";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("OGRETMEN_ID", "%" + name + "%"); // % işareti parameter içersinde olacak
		return namedParameterJdbcTemplate.query(sql, paramMap, BeanPropertyRowMapper.newInstance(Ders.class));
	}


}