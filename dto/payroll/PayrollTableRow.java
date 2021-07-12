package tz.go.tcra.hrms.dto.payroll;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayrollTableRow implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SerializedName(value = "sn")
	private String sn;
	@SerializedName(value = "fullName")
	private String fullName;
	@SerializedName(value = "salaryBasic")
	private String salaryBasic;
	@SerializedName(value = "wages")
	private String wages;
	@SerializedName(value = "allowanceHospitality")
	private String allowanceHospitality;
	@SerializedName(value = "allowanceHousing")
	private String allowanceHousing;
	@SerializedName(value = "allowanceTransport")
	private String allowanceTransport;
	@SerializedName(value = "salaryGross")
	private String salaryGross;
	@SerializedName(value = "salaryTaxable")
	private String salaryTaxable;
	@SerializedName(value = "taxPaye")
	private String taxPaye;
	@SerializedName(value = "pensionPSSSF")
	private String pensionPSSSF;
	@SerializedName(value = "pensionPSSSFEmployer")
	private String pensionPSSSFEmployer;
	@SerializedName(value = "pensionZSSF")
	private String pensionZSSF;
	@SerializedName(value = "pensionZSSFEmployer")
	private String pensionZSSFEmployer;
	@SerializedName(value = "loanAzaniaBank")
	private String loanAzaniaBank;
	@SerializedName(value = "loanResidential")
	private String loanResidential;
	@SerializedName(value = "loanResidentialBalance")
	private String loanResidentialBalance;
	@SerializedName(value = "loanTransport")
	private String loanTransport;
	@SerializedName(value = "loanTransportBalance")
	private String loanTransportBalance;
	@SerializedName(value = "loanHESLB")
	private String loanHESLB;
	@SerializedName(value = "loanHESLBBalance")
	private String loanHESLBBalance;
	@SerializedName(value = "insuranceJubilee")
	private String insuranceJubilee;
	@SerializedName(value = "insuranceNIC")
	private String insuranceNIC;
	@SerializedName(value = "voluntaryPostaSimu")
	private String voluntaryPostaSimu;
	@SerializedName(value = "voluntaryTCRASACCOS")
	private String voluntaryTCRASACCOS;
	@SerializedName(value = "voluntaryTCRAMKM")
	private String voluntaryTCRAMKM;
	@SerializedName(value = "loanSalaryAdvance")
	private String loanSalaryAdvance;
	@SerializedName(value = "loanSalaryAdvanceBalance")
	private String loanSalaryAdvanceBalance;
	@SerializedName(value = "totalDeduction")
	private String totalDeduction;
	@SerializedName(value = "salaryNetPay")
	private String salaryNetPay;
}
