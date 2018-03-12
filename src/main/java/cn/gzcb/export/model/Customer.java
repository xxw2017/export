package cn.gzcb.export.model;

import cn.gzcb.export.strategypattern.exception.InputIllegalException;
import cn.gzcb.export.strategypattern.exception.ParametersIllegalException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xiongxianwei
 * 2018/3/6
 */
public class Customer {

    //private Desensitization service=Desensitization.getDesensitization(null);

    private int customer_id;//客户id
    private String cust_name;//客户名
    private String cust_sex;//性别
    private Integer cust_age;//年龄
    private String cust_birthday;//出生日期
    private String cust_merry;//婚姻情况
    private String cust_id_type;//证件类型
    private String cust_id_no;//证件号
    private String is_manage_owner;//是否经营业主
    private String project_code;//项目代码
    private String special_code;//例批代码
    private String profession_code;//行业代码
    private String branch_office;//分支行
    private String cust_manager_id;//客户经理id
    private String cust_manager_name;//客户经理姓名
    private String getCust_manager_grade;//当前经理评级
    private String has_children;//子女情况
    private String census_register;//户籍
    private String phone1;//手机1
    private String phone2;//手机2
    private String pbc_phone;//人行电话
    private String company_phone1;//单位电话1
    private String company_phone2;//单位电话2
    private String home_phone1;//住宅电话1
    private String company_name1;//单位名称1
    private String company_name2;//单位名称2
    private String company_addr1;//单位地址1
    private String company_addr2;//单位地址2
    private String home_addr1;//住宅地址1
    private String home_addr2;//住宅地址2
    private String created_time;//创建时间
    private String created_by;//创建人
    private String updated_time;//更新时间
    private String update_by;//更新人

    @Override
    public String toString() {
        StringBuffer sb=new StringBuffer();
        sb.append(customer_id+",");
            //sb.append(desensitization.getStrategy("cust_name").removeSensitive(cust_name)+",");
            sb.append(cust_name+",");
            sb.append(cust_sex+",");
            sb.append(cust_age+",");
            sb.append(cust_birthday+",");
            sb.append(cust_merry+",");
            sb.append(cust_id_type+",");
            //sb.append(desensitization.getStrategy("cust_id_no").removeSensitive(cust_id_no)+",");
            sb.append(is_manage_owner+",");
            sb.append(project_code+",");
            sb.append(special_code+",");
            sb.append(profession_code+",");
            sb.append(branch_office+",");
            sb.append(cust_manager_id+",");
            sb.append(cust_manager_name+",");
            sb.append(getCust_manager_grade+",");
            sb.append(has_children+",");
            sb.append(census_register+",");
            sb.append(phone1+",");
            sb.append(phone2+",");
            sb.append(pbc_phone+",");
            sb.append(company_phone1+",");
            sb.append(company_phone2+",");
            sb.append(home_phone1+",");
//            sb.append(desensitization.getStrategy("phone1").removeSensitive(phone1)+",");
//            sb.append(desensitization.getStrategy("phone2").removeSensitive(phone2)+",");
//            sb.append(desensitization.getStrategy("pbc_phone").removeSensitive(pbc_phone)+",");
//            sb.append(desensitization.getStrategy("company_phone1").removeSensitive(company_phone1)+",");
//            sb.append(desensitization.getStrategy("company_phone2").removeSensitive(company_phone2)+",");
//            sb.append(desensitization.getStrategy("home_phone1").removeSensitive(home_phone1)+",");
            sb.append(company_name1+",");
            sb.append(company_name2+",");
            sb.append(company_addr1+",");
            sb.append(company_addr2+",");
            sb.append(home_addr1+",");
            sb.append(home_addr2+",");
//            sb.append(desensitization.getStrategy("company_addr1").removeSensitive(company_addr1)+",");
//            sb.append(desensitization.getStrategy("company_addr2").removeSensitive(company_addr2)+",");
//            sb.append(desensitization.getStrategy("home_addr1").removeSensitive(home_addr1)+",");
//            sb.append(desensitization.getStrategy("home_addr2").removeSensitive(home_addr2)+",");
            sb.append(created_time+",");
            sb.append(created_by+",");
            sb.append(updated_time+",");
            sb.append(update_by);

        return sb.toString();
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getCust_sex() {
        return cust_sex;
    }

    public void setCust_sex(String cust_sex) {
        this.cust_sex = cust_sex;
    }

    public Integer getCust_age() {
        return cust_age;
    }

    public void setCust_age(Integer cust_age) {
        this.cust_age = cust_age;
    }

    public String getCust_birthday() {
        return cust_birthday;
    }

    public void setCust_birthday(String cust_birthday) {
        this.cust_birthday = cust_birthday;
    }

    public String getCust_merry() {
        return cust_merry;
    }

    public void setCust_merry(String cust_merry) {
        this.cust_merry = cust_merry;
    }

    public String getCust_id_type() {
        return cust_id_type;
    }

    public void setCust_id_type(String cust_id_type) {
        this.cust_id_type = cust_id_type;
    }

    public String getCust_id_no() {
        return cust_id_no;
    }

    public void setCust_id_no(String cust_id_no) {
        this.cust_id_no = cust_id_no;
    }

    public String getIs_manage_owner() {
        return is_manage_owner;
    }

    public void setIs_manage_owner(String is_manage_owner) {
        this.is_manage_owner = is_manage_owner;
    }

    public String getProject_code() {
        return project_code;
    }

    public void setProject_code(String project_code) {
        this.project_code = project_code;
    }

    public String getSpecial_code() {
        return special_code;
    }

    public void setSpecial_code(String special_code) {
        this.special_code = special_code;
    }

    public String getProfession_code() {
        return profession_code;
    }

    public void setProfession_code(String profession_code) {
        this.profession_code = profession_code;
    }

    public String getBranch_office() {
        return branch_office;
    }

    public void setBranch_office(String branch_office) {
        this.branch_office = branch_office;
    }

    public String getCust_manager_id() {
        return cust_manager_id;
    }

    public void setCust_manager_id(String cust_manager_id) {
        this.cust_manager_id = cust_manager_id;
    }

    public String getCust_manager_name() {
        return cust_manager_name;
    }

    public void setCust_manager_name(String cust_manager_name) {
        this.cust_manager_name = cust_manager_name;
    }

    public String getGetCust_manager_grade() {
        return getCust_manager_grade;
    }

    public void setGetCust_manager_grade(String getCust_manager_grade) {
        this.getCust_manager_grade = getCust_manager_grade;
    }

    public String getHas_children() {
        return has_children;
    }

    public void setHas_children(String has_children) {
        this.has_children = has_children;
    }

    public String getCensus_register() {
        return census_register;
    }

    public void setCensus_register(String census_register) {
        this.census_register = census_register;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPbc_phone() {
        return pbc_phone;
    }

    public void setPbc_phone(String pbc_phone) {
        this.pbc_phone = pbc_phone;
    }

    public String getCompany_phone1() {
        return company_phone1;
    }

    public void setCompany_phone1(String company_phone1) {
        this.company_phone1 = company_phone1;
    }

    public String getCompany_phone2() {
        return company_phone2;
    }

    public void setCompany_phone2(String company_phone2) {
        this.company_phone2 = company_phone2;
    }

    public String getHome_phone1() {
        return home_phone1;
    }

    public void setHome_phone1(String home_phone1) {
        this.home_phone1 = home_phone1;
    }

    public String getCompany_name1() {
        return company_name1;
    }

    public void setCompany_name1(String company_name1) {
        this.company_name1 = company_name1;
    }

    public String getCompany_name2() {
        return company_name2;
    }

    public void setCompany_name2(String company_name2) {
        this.company_name2 = company_name2;
    }

    public String getCompany_addr1() {
        return company_addr1;
    }

    public void setCompany_addr1(String company_addr1) {
        this.company_addr1 = company_addr1;
    }

    public String getCompany_addr2() {
        return company_addr2;
    }

    public void setCompany_addr2(String company_addr2) {
        this.company_addr2 = company_addr2;
    }

    public String getHome_addr1() {
        return home_addr1;
    }

    public void setHome_addr1(String home_addr1) {
        this.home_addr1 = home_addr1;
    }

    public String getHome_addr2() {
        return home_addr2;
    }

    public void setHome_addr2(String home_addr2) {
        this.home_addr2 = home_addr2;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(String updated_time) {
        this.updated_time = updated_time;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }
}
