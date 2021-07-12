package tz.go.tcra.hrms.training.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HrmsTrainingReport implements Serializable{

	private static final long serialVersionUID = 1L;
	private int financialyearid;
	private int quaterid;
	private int trainingcategoryid;
}
