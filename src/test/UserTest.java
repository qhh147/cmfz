import com.baizhi.App;
import com.baizhi.dao.UserMapper;
import com.baizhi.entity.User;
import org.junit.internal.Classes;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by Administrator on 2018/6/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class UserTest {
    @Autowired
    private UserMapper userMapper;
    public void test1(){
        List<User> users = userMapper.queryAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
    public void test2(){
        User user = userMapper.queryById(1);
        System.out.println(user);
    }
}
