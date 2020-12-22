package ${basePackage}.service.impl;

import ${basePackage}.mapper.impl.${modelNameUpperCamel}Mapper;
import ${basePackage}.pojo.domain.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import ${basePackage}.mapper.core.AbstractService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;


/**
* desc : ${modelNameUpperCamel}ServiceImpl
* @author ${author}
* @date ${date}
*/
@Service
public class ${modelNameUpperCamel}ServiceImpl extends AbstractService<${modelNameUpperCamel}> implements ${modelNameUpperCamel}Service {
    @Resource
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;

}
