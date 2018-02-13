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
        projectRepository.save(new Project(1401030, "default_140103", teamRepository.findOne(140103)));
        projectRepository.save(new Project(1401020, "default_140102", teamRepository.findOne(140102)));
        projectRepository.save(new Project(1401010, "default_140101", teamRepository.findOne(140101)));
    }

    private void initUser() {
        Team team;
        Project project;
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findOne(1));

        //14计科1班
        team = teamRepository.findOne(140101);
        project = projectRepository.findOne(1401010);

        userRepository.save(new User("20142805", team, "徐劲松", false, "20142805",roles,project,true));
        userRepository.save(new User("20142807", team, "赵伟业", false, "20142807",roles,project,true));
        userRepository.save(new User("20142810", team, "王健晖", false, "20142810",roles,project,true));
        userRepository.save(new User("20142817", team, "康佳成", false, "20142817",roles,project,true));
        userRepository.save(new User("20142819", team, "陈尚平", false, "20142819",roles,project,true));
        userRepository.save(new User("20142821", team, "郭玲玲", false, "20142821",roles,project,true));
        userRepository.save(new User("20142827", team, "汤志伟", false, "20142827",roles,project,true));
        userRepository.save(new User("20142828", team, "徐星星", false, "20142828",roles,project,true));
        userRepository.save(new User("20142835", team, "杜程程", false, "20142835",roles,project,true));
        userRepository.save(new User("20142838", team, "刘子藤", false, "20142838",roles,project,true));
        userRepository.save(new User("20142840", team, "徐慧慧", false, "20142840",roles,project,true));
        userRepository.save(new User("20142848", team, "唐传龙", false, "20142848",roles,project,true));
        userRepository.save(new User("20142849", team, "胡景", false, "20142849",roles,project,true));
        userRepository.save(new User("20142852", team, "王梦璇", false, "20142852",roles,project,true));
        userRepository.save(new User("20142855", team, "葛骞", false, "20142855",roles,project,true));
        userRepository.save(new User("20142861", team, "张小敏", false, "20142861",roles,project,true));
        userRepository.save(new User("20142866", team, "伍小平", false, "20142866",roles,project,true));
        userRepository.save(new User("20142868", team, "章翔", false, "20142868",roles,project,true));
        userRepository.save(new User("20142870", team, "徐梦园", false, "20142870",roles,project,true));
        userRepository.save(new User("20142873", team, "刘忠慧", false, "20142873",roles,project,true));
        userRepository.save(new User("20142879", team, "陈昊东", false, "20142879",roles,project,true));
        userRepository.save(new User("20142882", team, "周永泰", false, "20142882",roles,project,true));
        userRepository.save(new User("20142883", team, "马云龙", false, "20142883",roles,project,true));
        userRepository.save(new User("20142888", team, "汝昊天", false, "20142888",roles,project,true));
        userRepository.save(new User("20142890", team, "唐博强", false, "20142890",roles,project,true));
        userRepository.save(new User("20142893", team, "江子慧", false, "20142893",roles,project,true));
        userRepository.save(new User("20142894", team, "尉胜男", false, "20142894",roles,project,true));
        userRepository.save(new User("20142898", team, "刘皖肖", false, "20142898",roles,project,true));
        userRepository.save(new User("20142900", team, "郑智恒", false, "20142900",roles,project,true));
        userRepository.save(new User("20142902", team, "刘定生", false, "20142902",roles,project,true));
        userRepository.save(new User("20142905", team, "徐文阳", false, "20142905",roles,project,true));
        userRepository.save(new User("20142910", team, "聂玉玲", false, "20142910",roles,project,true));
        userRepository.save(new User("20142914", team, "左小雪", false, "20142914",roles,project,true));
        userRepository.save(new User("20142915", team, "陈大宝", false, "20142915",roles,project,true));
        userRepository.save(new User("20142918", team, "丰婷", false, "20142918",roles,project,true));
        userRepository.save(new User("20142924", team, "姚绍武", false, "20142924",roles,project,true));
        userRepository.save(new User("20142925", team, "程前", false, "20142925",roles,project,true));
        userRepository.save(new User("20142927", team, "倪文", false, "20142927",roles,project,true));
        userRepository.save(new User("20142928", team, "张海涛", false, "20142928",roles,project,true));
        userRepository.save(new User("20142931", team, "舒婷婷", false, "20142931",roles,project,true));
        userRepository.save(new User("20142934", team, "凡梦梦", false, "20142934",roles,project,true));
        userRepository.save(new User("20142937", team, "杨秋萍", false, "20142937",roles,project,true));
        userRepository.save(new User("20142941", team, "朱稳稳", false, "20142941",roles,project,true));
        userRepository.save(new User("20142946", team, "周威", false, "20142946",roles,project,true));
        userRepository.save(new User("20142948", team, "储小寒", false, "20142948",roles,project,true));
        userRepository.save(new User("20142951", team, "胡少晖", false, "20142951",roles,project,true));
        userRepository.save(new User("20144315", team, "马硕", false, "20144315",roles,project,true));


        team = teamRepository.findOne(140102);
        project = projectRepository.findOne(1401020);

        userRepository.save(new User("20142808", team, "卫孝贤", false, "20142808",roles,project,true));
        userRepository.save(new User("20142811", team, "李玉霞", false, "20142811",roles,project,true));
        userRepository.save(new User("20142812", team, "石文意", false, "20142812",roles,project,true));
        userRepository.save(new User("20142813", team, "陈劲宇", false, "20142813",roles,project,true));
        userRepository.save(new User("20142816", team, "孟玉", false, "20142816",roles,project,true));
        userRepository.save(new User("20142818", team, "张政", false, "20142818",roles,project,true));
        userRepository.save(new User("20142820", team, "奚志祥", false, "20142820",roles,project,true));
        userRepository.save(new User("20142822", team, "韩蕾", false, "20142822",roles,project,true));
        userRepository.save(new User("20142825", team, "蒲好天", false, "20142825",roles,project,true));
        userRepository.save(new User("20142829", team, "王泽宇", false, "20142829",roles,project,true));
        userRepository.save(new User("20142830", team, "郝梦洁", false, "20142830",roles,project,true));
        userRepository.save(new User("20142832", team, "陈理想", false, "20142832",roles,project,true));
        userRepository.save(new User("20142843", team, "李道临", false, "20142843",roles,project,true));
        userRepository.save(new User("20142845", team, "程汝梁", false, "20142845",roles,project,true));
        userRepository.save(new User("20142851", team, "李应斌", false, "20142851",roles,project,true));
        userRepository.save(new User("20142853", team, "方福潜", false, "20142853",roles,project,true));
        userRepository.save(new User("20142858", team, "周余红", false, "20142858",roles,project,true));
        userRepository.save(new User("20142862", team, "吴铳", false, "20142862",roles,project,true));
        userRepository.save(new User("20142867", team, "汪臻", false, "20142867",roles,project,true));
        userRepository.save(new User("20142872", team, "丁健", false, "20142872",roles,project,true));
        userRepository.save(new User("20142874", team, "关敬婷", false, "20142874",roles,project,true));
        userRepository.save(new User("20142876", team, "张周宇", false, "20142876",roles,project,true));
        userRepository.save(new User("20142878", team, "凡光辉", false, "20142878",roles,project,true));
        userRepository.save(new User("20142884", team, "孙文倩", false, "20142884",roles,project,true));
        userRepository.save(new User("20142886", team, "赵东旭", false, "20142886",roles,project,true));
        userRepository.save(new User("20142889", team, "姜文彪", false, "20142889",roles,project,true));
        userRepository.save(new User("20142895", team, "候梦如", false, "20142895",roles,project,true));
        userRepository.save(new User("20142896", team, "王培立", false, "20142896",roles,project,true));
        userRepository.save(new User("20142903", team, "王弄笛", false, "20142903",roles,project,true));
        userRepository.save(new User("20142904", team, "高仁杰", false, "20142904",roles,project,true));
        userRepository.save(new User("20142906", team, "范佳佳", false, "20142906",roles,project,true));
        userRepository.save(new User("20142908", team, "张灿", false, "20142908",roles,project,true));
        userRepository.save(new User("20142911", team, "许荣", false, "20142911",roles,project,true));
        userRepository.save(new User("20142916", team, "袁宏飞", false, "20142916",roles,project,true));
        userRepository.save(new User("20142921", team, "邱远豪", false, "20142921",roles,project,true));
        userRepository.save(new User("20142923", team, "王明治", false, "20142923",roles,project,true));
        userRepository.save(new User("20142933", team, "万文娟", false, "20142933",roles,project,true));
        userRepository.save(new User("20142935", team, "方远远", false, "20142935",roles,project,true));
        userRepository.save(new User("20142936", team, "张彪", false, "20142936",roles,project,true));
        userRepository.save(new User("20142939", team, "倪利伟", false, "20142939",roles,project,true));
        userRepository.save(new User("20142940", team, "高梦梦", false, "20142940",roles,project,true));
        userRepository.save(new User("20142945", team, "刘康", false, "20142945",roles,project,true));
        userRepository.save(new User("20142947", team, "石俊", false, "20142947",roles,project,true));
        userRepository.save(new User("20142949", team, "刘涛", false, "20142949",roles,project,true));
        userRepository.save(new User("20142950", team, "王涛", false, "20142950",roles,project,true));
        userRepository.save(new User("20144470", team, "张胜宝", false, "20144470",roles,project,true));


        team = teamRepository.findOne(140103);
        project = projectRepository.findOne(1401030);

        userRepository.save(new User("20142806", team, "王智豪", false, "20142806",roles,project,true));
        userRepository.save(new User("20142809", team, "许海浪", false, "20142809",roles,project,true));
        userRepository.save(new User("20142823", team, "魏玉强", false, "20142823",roles,project,true));
        userRepository.save(new User("20142824", team, "班名洋", false, "20142824",roles,project,true));
        userRepository.save(new User("20142826", team, "王洪蕊", false, "20142826",roles,project,true));
        userRepository.save(new User("20142831", team, "李俊茹", false, "20142831",roles,project,true));
        userRepository.save(new User("20142834", team, "邓小峰", false, "20142834",roles,project,true));
        userRepository.save(new User("20142836", team, "谢阳琴", false, "20142836",roles,project,true));
        userRepository.save(new User("20142841", team, "胡丹阳", false, "20142841",roles,project,true));
        userRepository.save(new User("20142846", team, "高鑫", false, "20142846",roles,project,true));
        userRepository.save(new User("20142847", team, "徐勇兵", false, "20142847",roles,project,true));
        userRepository.save(new User("20142850", team, "章园琴", false, "20142850",roles,project,true));
        userRepository.save(new User("20142854", team, "熊严秋", false, "20142854",roles,project,true));
        userRepository.save(new User("20142856", team, "陈胡杨", false, "20142856",roles,project,true));
        userRepository.save(new User("20142859", team, "吴丹红", false, "20142859",roles,project,true));
        userRepository.save(new User("20142860", team, "王兴", false, "20142860",roles,project,true));
        userRepository.save(new User("20142864", team, "刘佳君", false, "20142864",roles,project,true));
        userRepository.save(new User("20142865", team, "朱学锋", false, "20142865",roles,project,true));
        userRepository.save(new User("20142871", team, "张涛", false, "20142871",roles,project,true));
        userRepository.save(new User("20142875", team, "汪长春", false, "20142875",roles,project,true));
        userRepository.save(new User("20142877", team, "蔡颖", false, "20142877",roles,project,true));
        userRepository.save(new User("20142880", team, "何兆斌", false, "20142880",roles,project,true));
        userRepository.save(new User("20142885", team, "刘海洋", false, "20142885",roles,project,true));
        userRepository.save(new User("20142887", team, "张保玲", false, "20142887",roles,project,true));
        userRepository.save(new User("20142892", team, "殷闵琦", false, "20142892",roles,project,true));
        userRepository.save(new User("20142897", team, "王静", false, "20142897",roles,project,true));
        userRepository.save(new User("20142899", team, "王旋", false, "20142899",roles,project,true));
        userRepository.save(new User("20142901", team, "董丙冰", false, "20142901",roles,project,true));
        userRepository.save(new User("20142907", team, "黄艳", false, "20142907",roles,project,true));
        userRepository.save(new User("20142909", team, "李飞", false, "20142909",roles,project,true));
        userRepository.save(new User("20142912", team, "李刚", false, "20142912",roles,project,true));
        userRepository.save(new User("20142913", team, "孙杰", false, "20142913",roles,project,true));
        userRepository.save(new User("20142917", team, "郑伟", false, "20142917",roles,project,true));
        userRepository.save(new User("20142919", team, "李海燕", false, "20142919",roles,project,true));
        userRepository.save(new User("20142920", team, "丁瑞军", false, "20142920",roles,project,true));
        userRepository.save(new User("20142922", team, "李树", false, "20142922",roles,project,true));
        userRepository.save(new User("20142926", team, "苏芃", false, "20142926",roles,project,true));
        userRepository.save(new User("20142929", team, "柯健宇", false, "20142929",roles,project,true));
        userRepository.save(new User("20142930", team, "蔡近远", false, "20142930",roles,project,true));
        userRepository.save(new User("20142932", team, "蔡永杰", false, "20142932",roles,project,true));
        userRepository.save(new User("20142938", team, "郑乐", false, "20142938",roles,project,true));
        userRepository.save(new User("20142942", team, "熊志敏", false, "20142942",roles,project,true));
        userRepository.save(new User("20142944", team, "张雨星", false, "20142944",roles,project,true));
        userRepository.save(new User("20143461", team, "李新宇", false, "20143461",roles,project,true));
        userRepository.save(new User("20144407", team, "陈玮琪", false, "20144407",roles,project,true));

        //为李新宇添加管理员权限
        //User user = userRepository.findOne(20143461);
        //user.addRole(roleRepository.findOne(0));
        //userRepository.save(user);
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

        codeRepository.save(new Code(1, "main.c", s, "c", projectRepository.findOne(1401030), true));
        codeRepository.save(new Code(2, "readme.md", "毕业设计readme", "text", projectRepository.findOne(1401030), false));
    }
}
