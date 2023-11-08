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

import com.bilgeadam.springrest.model.Ogretmen;

@Repository
public class OgretmenRepository
{
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setJdbcTemplate(@Qualifier(value = "bizimjdbctemplate") JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public List<Ogretmen> getAll()
	{
		return jdbcTemplate.query("select * from \"public\".\"OGRETMEN\" order by \"ID\" asc", BeanPropertyRowMapper.newInstance(Ogretmen.class));
	}

	public boolean deleteByID(long id)
	{
		String sql = "delete from \"public\".\"OGRETMEN\" where \"ID\" = :ID";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ID", id);
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}

	public Ogretmen getByID(long id)
	{
		Ogretmen ogretmen = null;
		String sql = "select * from \"public\".\"OGRETMEN\" where \"ID\" = :ABUZIDDIN";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ABUZIDDIN", id);
		ogretmen = namedParameterJdbcTemplate.queryForObject(sql, paramMap, BeanPropertyRowMapper.newInstance(Ogretmen.class));
	
		return ogretmen;
	}

	public boolean save(Ogretmen ogr)
	{
		String sql = "insert into \"public\".\"OGRETMEN\" (\"NAME\", \"IS_GICIK\") values (:NAME, :GICIK)";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("NAME", ogr.getNAME());
		paramMap.put("GICIK", ogr.isIS_GICIK());
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}

	public List<Ogretmen> getAllLike(String name)
	{
		String sql = "select * from \"public\".\"OGRETMEN\" where \"NAME\" LIKE :NAME";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("NAME", "%" + name + "%"); // % işareti parameter içersinde olacak
		return namedParameterJdbcTemplate.query(sql, paramMap, BeanPropertyRowMapper.newInstance(Ogretmen.class));
	}

//	public boolean update(long id, Ogretmen ogr) throws SQLException
//	{
//	}
}