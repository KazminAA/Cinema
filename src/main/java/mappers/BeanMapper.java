package mappers;

import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lex on 13.09.16.
 */
public class BeanMapper {
    private static BeanMapper beanMapper;
    private static DozerBeanMapper mapper;

    private BeanMapper() {
        mapper = new DozerBeanMapper();
        List<String> mappingFiles = new ArrayList();
        mappingFiles.add(BeanMapper.class.getClassLoader().getResource("dozerJdk8Converters.xml").toString());
        mappingFiles.add(BeanMapper.class.getClassLoader().getResource("dozerMapping.xml").toString());
        mapper.setMappingFiles(mappingFiles);
    }

    public static synchronized BeanMapper getInstance() {
        if (beanMapper == null) {
            beanMapper = new BeanMapper();
        }
        return beanMapper;
    }

    public static <T> T singleMapper(Object from, Class<T> toClass) {
        T result = mapper.map(from, toClass);
        return result;
    }

    public static <E, T> List<T> listMapToList(Iterable<E> iterable, Class<T> toClass) {
        List<T> result = new LinkedList<>();
        for (E e : iterable) {
            result.add(mapper.map(e, toClass));
        }
        return result;
    }
}
