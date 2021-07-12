package tz.go.tcra.hrms.dto.leave;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnLeave implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int employeeid;
	private String enddate;
	private int onleave;
}
