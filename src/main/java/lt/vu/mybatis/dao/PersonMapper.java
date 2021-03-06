package lt.vu.mybatis.dao;

import java.util.List;
import lt.vu.mybatis.model.Person;
import org.mybatis.cdi.Mapper;

@Mapper
public interface PersonMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PERSON
     *
     * @mbg.generated Tue Apr 14 11:10:38 EEST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PERSON
     *
     * @mbg.generated Tue Apr 14 11:10:38 EEST 2020
     */
    int insert(Person record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PERSON
     *
     * @mbg.generated Tue Apr 14 11:10:38 EEST 2020
     */
    Person selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PERSON
     *
     * @mbg.generated Tue Apr 14 11:10:38 EEST 2020
     */
    List<Person> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PERSON
     *
     * @mbg.generated Tue Apr 14 11:10:38 EEST 2020
     */
    int updateByPrimaryKey(Person record);
}