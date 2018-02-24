package com.lixinyu.cooperativecoding.util;

import com.lixinyu.cooperativecoding.data.*;
import com.lixinyu.cooperativecoding.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

//初始化数据库(产生测试数据)
@Component
public class Initializr implements CommandLineRunner {
    private final CodeRepository codeRepository;
    private final ProjectRepository projectRepository;
    private final RoleRepository roleRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    @Autowired
    public Initializr(CodeRepository codeRepository, ProjectRepository projectRepository, RoleRepository roleRepository, TeamRepository teamRepository, UserRepository userRepository) {
        this.codeRepository = codeRepository;
        this.projectRepository = projectRepository;
        this.roleRepository = roleRepository;
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... strings) {
        initRole();
        initTeam();

        initProject();
        initUser();
        initCode();
    }
    /*
    * Administrator 系统管理员
    * User          普通用户
    * Guest         游客
    */
    private void initRole() {

        roleRepository.save(new Role(0, "Administrator"));
        roleRepository.save(new Role(1, "User"));
        roleRepository.save(new Role(2, "Guest"));
    }

    private void initTeam() {
        teamRepository.save(new Team(140101, "14计科1班"));
        teamRepository.save(new Team(140102, "14计科2班"));
        teamRepository.save(new Team(140103, "14计科3班"));
    }

    private void initProject() {
        //14计科3班的project
        projectRepository.save(new Project(1401010, "default_140101", teamRepository.findOne(140101)));
        projectRepository.save(new Project(1401020, "default_140102", teamRepository.findOne(140102)));
        projectRepository.save(new Project(1401030, "default_140103", teamRepository.findOne(140103)));

        projectRepository.save(new Project(1401031, "second_140103", teamRepository.findOne(140103)));
    }

    private void initUser() {
        Team team;
        Project project;
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findOne(1));

        /*
        //14计科1班
        team = teamRepository.findOne(140101);
        project = projectRepository.findOne(1401010);

        userRepository.save(new User("20142805", team, "徐劲松","20142805",roles,project,true));
        userRepository.save(new User("20142807", team, "赵伟业","20142807",roles,project,true));
        userRepository.save(new User("20142810", team, "王健晖","20142810",roles,project,true));
        userRepository.save(new User("20142817", team, "康佳成","20142817",roles,project,true));
        userRepository.save(new User("20142819", team, "陈尚平","20142819",roles,project,true));
        userRepository.save(new User("20142821", team, "郭玲玲","20142821",roles,project,true));
        userRepository.save(new User("20142827", team, "汤志伟","20142827",roles,project,true));
        userRepository.save(new User("20142828", team, "徐星星","20142828",roles,project,true));
        userRepository.save(new User("20142835", team, "杜程程","20142835",roles,project,true));
        userRepository.save(new User("20142838", team, "刘子藤","20142838",roles,project,true));
        userRepository.save(new User("20142840", team, "徐慧慧","20142840",roles,project,true));
        userRepository.save(new User("20142848", team, "唐传龙","20142848",roles,project,true));
        userRepository.save(new User("20142849", team, "胡景","20142849",roles,project,true));
        userRepository.save(new User("20142852", team, "王梦璇","20142852",roles,project,true));
        userRepository.save(new User("20142855", team, "葛骞","20142855",roles,project,true));
        userRepository.save(new User("20142861", team, "张小敏","20142861",roles,project,true));
        userRepository.save(new User("20142866", team, "伍小平","20142866",roles,project,true));
        userRepository.save(new User("20142868", team, "章翔","20142868",roles,project,true));
        userRepository.save(new User("20142870", team, "徐梦园","20142870",roles,project,true));
        userRepository.save(new User("20142873", team, "刘忠慧","20142873",roles,project,true));
        userRepository.save(new User("20142879", team, "陈昊东","20142879",roles,project,true));
        userRepository.save(new User("20142882", team, "周永泰","20142882",roles,project,true));
        userRepository.save(new User("20142883", team, "马云龙","20142883",roles,project,true));
        userRepository.save(new User("20142888", team, "汝昊天","20142888",roles,project,true));
        userRepository.save(new User("20142890", team, "唐博强","20142890",roles,project,true));
        userRepository.save(new User("20142893", team, "江子慧","20142893",roles,project,true));
        userRepository.save(new User("20142894", team, "尉胜男","20142894",roles,project,true));
        userRepository.save(new User("20142898", team, "刘皖肖","20142898",roles,project,true));
        userRepository.save(new User("20142900", team, "郑智恒","20142900",roles,project,true));
        userRepository.save(new User("20142902", team, "刘定生","20142902",roles,project,true));
        userRepository.save(new User("20142905", team, "徐文阳","20142905",roles,project,true));
        userRepository.save(new User("20142910", team, "聂玉玲","20142910",roles,project,true));
        userRepository.save(new User("20142914", team, "左小雪","20142914",roles,project,true));
        userRepository.save(new User("20142915", team, "陈大宝","20142915",roles,project,true));
        userRepository.save(new User("20142918", team, "丰婷","20142918",roles,project,true));
        userRepository.save(new User("20142924", team, "姚绍武","20142924",roles,project,true));
        userRepository.save(new User("20142925", team, "程前","20142925",roles,project,true));
        userRepository.save(new User("20142927", team, "倪文","20142927",roles,project,true));
        userRepository.save(new User("20142928", team, "张海涛","20142928",roles,project,true));
        userRepository.save(new User("20142931", team, "舒婷婷","20142931",roles,project,true));
        userRepository.save(new User("20142934", team, "凡梦梦","20142934",roles,project,true));
        userRepository.save(new User("20142937", team, "杨秋萍","20142937",roles,project,true));
        userRepository.save(new User("20142941", team, "朱稳稳","20142941",roles,project,true));
        userRepository.save(new User("20142946", team, "周威","20142946",roles,project,true));
        userRepository.save(new User("20142948", team, "储小寒","20142948",roles,project,true));
        userRepository.save(new User("20142951", team, "胡少晖","20142951",roles,project,true));
        userRepository.save(new User("20144315", team, "马硕","20144315",roles,project,true));


        team = teamRepository.findOne(140102);
        project = projectRepository.findOne(1401020);

        userRepository.save(new User("20142808", team, "卫孝贤","20142808",roles,project,true));
        userRepository.save(new User("20142811", team, "李玉霞","20142811",roles,project,true));
        userRepository.save(new User("20142812", team, "石文意","20142812",roles,project,true));
        userRepository.save(new User("20142813", team, "陈劲宇","20142813",roles,project,true));
        userRepository.save(new User("20142816", team, "孟玉","20142816",roles,project,true));
        userRepository.save(new User("20142818", team, "张政","20142818",roles,project,true));
        userRepository.save(new User("20142820", team, "奚志祥","20142820",roles,project,true));
        userRepository.save(new User("20142822", team, "韩蕾","20142822",roles,project,true));
        userRepository.save(new User("20142825", team, "蒲好天","20142825",roles,project,true));
        userRepository.save(new User("20142829", team, "王泽宇","20142829",roles,project,true));
        userRepository.save(new User("20142830", team, "郝梦洁","20142830",roles,project,true));
        userRepository.save(new User("20142832", team, "陈理想","20142832",roles,project,true));
        userRepository.save(new User("20142843", team, "李道临","20142843",roles,project,true));
        userRepository.save(new User("20142845", team, "程汝梁","20142845",roles,project,true));
        userRepository.save(new User("20142851", team, "李应斌","20142851",roles,project,true));
        userRepository.save(new User("20142853", team, "方福潜","20142853",roles,project,true));
        userRepository.save(new User("20142858", team, "周余红","20142858",roles,project,true));
        userRepository.save(new User("20142862", team, "吴铳","20142862",roles,project,true));
        userRepository.save(new User("20142867", team, "汪臻","20142867",roles,project,true));
        userRepository.save(new User("20142872", team, "丁健","20142872",roles,project,true));
        userRepository.save(new User("20142874", team, "关敬婷","20142874",roles,project,true));
        userRepository.save(new User("20142876", team, "张周宇","20142876",roles,project,true));
        userRepository.save(new User("20142878", team, "凡光辉","20142878",roles,project,true));
        userRepository.save(new User("20142884", team, "孙文倩","20142884",roles,project,true));
        userRepository.save(new User("20142886", team, "赵东旭","20142886",roles,project,true));
        userRepository.save(new User("20142889", team, "姜文彪","20142889",roles,project,true));
        userRepository.save(new User("20142895", team, "候梦如","20142895",roles,project,true));
        userRepository.save(new User("20142896", team, "王培立","20142896",roles,project,true));
        userRepository.save(new User("20142903", team, "王弄笛","20142903",roles,project,true));
        userRepository.save(new User("20142904", team, "高仁杰","20142904",roles,project,true));
        userRepository.save(new User("20142906", team, "范佳佳","20142906",roles,project,true));
        userRepository.save(new User("20142908", team, "张灿","20142908",roles,project,true));
        userRepository.save(new User("20142911", team, "许荣","20142911",roles,project,true));
        userRepository.save(new User("20142916", team, "袁宏飞","20142916",roles,project,true));
        userRepository.save(new User("20142921", team, "邱远豪","20142921",roles,project,true));
        userRepository.save(new User("20142923", team, "王明治","20142923",roles,project,true));
        userRepository.save(new User("20142933", team, "万文娟","20142933",roles,project,true));
        userRepository.save(new User("20142935", team, "方远远","20142935",roles,project,true));
        userRepository.save(new User("20142936", team, "张彪","20142936",roles,project,true));
        userRepository.save(new User("20142939", team, "倪利伟","20142939",roles,project,true));
        userRepository.save(new User("20142940", team, "高梦梦","20142940",roles,project,true));
        userRepository.save(new User("20142945", team, "刘康","20142945",roles,project,true));
        userRepository.save(new User("20142947", team, "石俊","20142947",roles,project,true));
        userRepository.save(new User("20142949", team, "刘涛","20142949",roles,project,true));
        userRepository.save(new User("20142950", team, "王涛","20142950",roles,project,true));
        userRepository.save(new User("20144470", team, "张胜宝","20144470",roles,project,true));
        */

        team = teamRepository.findOne(140103);
        project = projectRepository.findOne(1401030);

        /*
        userRepository.save(new User("20142806", team, "王智豪","20142806",roles,project,true));
        userRepository.save(new User("20142809", team, "许海浪","20142809",roles,project,true));
        userRepository.save(new User("20142823", team, "魏玉强","20142823",roles,project,true));
        userRepository.save(new User("20142824", team, "班名洋","20142824",roles,project,true));
        userRepository.save(new User("20142826", team, "王洪蕊","20142826",roles,project,true));
        userRepository.save(new User("20142831", team, "李俊茹","20142831",roles,project,true));
        userRepository.save(new User("20142834", team, "邓小峰","20142834",roles,project,true));
        userRepository.save(new User("20142836", team, "谢阳琴","20142836",roles,project,true));
        userRepository.save(new User("20142841", team, "胡丹阳","20142841",roles,project,true));
        userRepository.save(new User("20142846", team, "高鑫","20142846",roles,project,true));
        userRepository.save(new User("20142847", team, "徐勇兵","20142847",roles,project,true));
        userRepository.save(new User("20142850", team, "章园琴","20142850",roles,project,true));
        userRepository.save(new User("20142854", team, "熊严秋","20142854",roles,project,true));
        userRepository.save(new User("20142856", team, "陈胡杨","20142856",roles,project,true));
        userRepository.save(new User("20142859", team, "吴丹红","20142859",roles,project,true));
        userRepository.save(new User("20142860", team, "王兴","20142860",roles,project,true));
        userRepository.save(new User("20142864", team, "刘佳君","20142864",roles,project,true));
        userRepository.save(new User("20142865", team, "朱学锋","20142865",roles,project,true));
        userRepository.save(new User("20142871", team, "张涛","20142871",roles,project,true));
        userRepository.save(new User("20142875", team, "汪长春","20142875",roles,project,true));
        userRepository.save(new User("20142877", team, "蔡颖","20142877",roles,project,true));
        userRepository.save(new User("20142880", team, "何兆斌","20142880",roles,project,true));
        userRepository.save(new User("20142885", team, "刘海洋","20142885",roles,project,true));
        userRepository.save(new User("20142887", team, "张保玲","20142887",roles,project,true));
        userRepository.save(new User("20142892", team, "殷闵琦","20142892",roles,project,true));
        userRepository.save(new User("20142897", team, "王静","20142897",roles,project,true));
        userRepository.save(new User("20142899", team, "王旋","20142899",roles,project,true));
        userRepository.save(new User("20142901", team, "董丙冰","20142901",roles,project,true));
        userRepository.save(new User("20142907", team, "黄艳","20142907",roles,project,true));
        userRepository.save(new User("20142909", team, "李飞","20142909",roles,project,true));
        userRepository.save(new User("20142912", team, "李刚","20142912",roles,project,true));
        userRepository.save(new User("20142913", team, "孙杰","20142913",roles,project,true));
        userRepository.save(new User("20142917", team, "郑伟","20142917",roles,project,true));
        userRepository.save(new User("20142919", team, "李海燕","20142919",roles,project,true));
        userRepository.save(new User("20142920", team, "丁瑞军","20142920",roles,project,true));
        userRepository.save(new User("20142922", team, "李树","20142922",roles,project,true));
        userRepository.save(new User("20142926", team, "苏芃","20142926",roles,project,true));
        userRepository.save(new User("20142929", team, "柯健宇","20142929",roles,project,true));
        userRepository.save(new User("20142930", team, "蔡近远","20142930",roles,project,true));
        userRepository.save(new User("20142932", team, "蔡永杰","20142932",roles,project,true));
        userRepository.save(new User("20142938", team, "郑乐","20142938",roles,project,true));
        userRepository.save(new User("20142942", team, "熊志敏","20142942",roles,project,true));
        userRepository.save(new User("20142944", team, "张雨星","20142944",roles,project,true));
        */
        userRepository.save(new User("20144407", team, "陈玮琪","20144407",roles,project,true));
        roles.add(roleRepository.findOne(0));
        userRepository.save(new User("20143461", team, "李新宇","20143461",roles,project,true));

        //为李新宇添加管理员权限
        //User user = userRepository.findOne(20143461);
        //user.addRole(roleRepository.findOne(0));
        //userRepository.save(user);
        Project p1 = projectRepository.findOne(1401030);
        p1.setMaintainer(userRepository.findByUsername("20143461").get());
        //14计科3班的project
        projectRepository.save(p1);
    }

    private void initCode() {
        String s = "#include <stdio.h>\n" +
                "int main()\n" +
                "{\n" +
                "    int i,j,k;\n" +
                "    for(i=0;i<=3;i++) {\n" +
                "        for(j=0;j<=2-i;j++) {\n" +
                "            printf(\" \");\n" +
                "        }\n" +
                "        for(k=0;k<=2*i;k++) {\n" +
                "            printf(\"*\");\n" +
                "        }\n" +
                "        printf(\"\\n\");\n" +
                "    }\n" +
                "    for(i=0;i<=2;i++) {\n" +
                "        for(j=0;j<=i;j++) {\n" +
                "            printf(\" \");\n" +
                "        }\n" +
                "        for(k=0;k<=4-2*i;k++) {\n" +
                "            printf(\"*\");\n" +
                "        }\n" +
                "        printf(\"\\n\");\n" +
                "    }\n" +
                "  \n" +
                "}";

        Code c1 = new Code("main.c", s, "c", projectRepository.findOne(1401030), true);
        codeRepository.save(c1);
        codeRepository.save(new Code("readme.md", "毕业设计readme", "text", projectRepository.findOne(1401030), false));
        codeRepository.save(new Code("main.c", "second project of 140103", "c", projectRepository.findOne(1401031), true));
    }
}
