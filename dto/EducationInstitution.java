package tz.go.tcra.hrms.dto;

import java.io.Serializable;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationInstitution implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String abbreviation;
	private int categoryId;
	private int cityId;
	private int countryId;
	private String description;
	private String name;
	private int typeid;
	private String type;
	private String category;
	private String city;
	private String country;
	private int id;

}
