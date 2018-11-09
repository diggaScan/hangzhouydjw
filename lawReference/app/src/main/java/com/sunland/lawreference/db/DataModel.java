package com.sunland.lawreference.db;

import com.sunland.lawreference.R;

public class DataModel {


    public static String[] law_category = {"处警提示", "执法标准"};

    public static String level[] = {"案件类警情处置", "纠纷类警情处置", "救助类警情处置"};

    // 案件类警情处置
    public static String level_1[] = {"侵财类案件警情处置", "侵犯人身类案件警情处置",
            "侵犯社会管理秩序类案件警情处置"};

    // 侵财类案件警情处置
    public static String level_1_1[] = {"盗窃", "抢劫、抢夺", "诈骗", "敲诈勒索", "侵占",
            "损毁公私财物"};
    public static String level_1_1_EN[] = {"", "qiangjieqiangduo", "zhapian",
            "qiaozhalesuo", "qinzhan", "sunhuigongsicaiwu"};

    // 盗窃
    public static String level_1_1_1[] = {"入户盗窃", "扒窃", "盗窃电动车", "盗窃车内物品"};
    public static String level_1_1_1_EN[] = {"ruhudaoqie", "paqie",
            "daoqiediandongche", "daoqiecheneiwupin"};


    // 侵犯人身类案件警情处置
    public static String level_1_2[] = {"伤害、命案", "强奸、猥亵", "侮辱、诽谤", "绑架、非法拘禁"};
    public static String level_1_2_EN[] = {"shanghaimingan", "qiangjian",
            "wuruhuibang", "bangjia"};


    // 侵犯社会管理秩序类案件警情处置
    public static String level_1_3[] = {"妨碍公务", "寻衅滋事", "聚众斗殴", "卖淫嫖娼", "赌博",
            "吸毒"};
    public static String level_1_3_EN[] = {"fangaigongwu", "xunxinzishi",
            "juzhongdouou", "maiyinpiaochang", "dubo", "xidu"};

    // 纠纷类警情处置
    public static String level_2[] = {"债务纠纷", "邻里纠纷", "家庭纠纷", "劳资纠纷", "消费纠纷",
            "物业纠纷", "假币纠纷", "噪音纠纷", "停车纠纷", "医患纠纷", "动物袭击他人"};
    public static String level_2_EN[] = {"zhaiwujiufen", "linlijiufen",
            "jiatingjiufen", "laozijiufen", "xiaofeijiufen", "wuyejiufen",
            "jiabijiufen", "zaoyinjiufen", "tingchejiufen", "yihuanjiufen",
            "dongwuxijitaren"};

    // 救助类警情处置
    public static String level_3[] = {"失踪人员(走失)", "失物", "扬言自杀", "醉酒", "昏厥",
            "开锁求助", "迷途、流浪、乞讨人员", "溺水", "楼上漏水", "高空坠物", "精神病人肇事肇祸", "弃婴(遗弃儿童)",
            "住宅内燃气泄漏", "水、电、气、热险情求助", "有毒、易燃、易爆、放射线物质泄漏(化学求援)", "火灾", "坠楼",
            "重大交通事故"};
    public static String level_3_EN[] = {"shizongrenyuan", "shiwu",
            "yangyanzisha", "zuijiu", "hunjue", "kaisuoqiuzhu", "mituliulang",
            "nishui", "loushangloushui", "gaokongzhuiwu", "jingshenbingren",
            "yiqiertong", "zhuzhaineiranqixielou", "shuidianqiqiuzhu",
            "huaxueqiuyuan", "huozai", "zhuilou", "zhongdajiaotongshigu"};

    //执法标准
    public static String[] fileNameCN = {"杭州公安民警礼仪规范", "杭州市公安机关案(事)件现场媒体引导标准操作程序",
            "杭州市公安机关处置不明原因死亡警情操作规范", "杭州市公安机关处置常见救助警情标准操作程序",
            "杭州市公安机关处置溺水警情操作规范", "杭州市公安机关处置使用假币案（事）件警情标准操作程序",
            "杭州市公安机关处置醉酒警情标准操作程序", "杭州市公安机关大型群众性活动现场安全管理标准操作程序(试行)",
            "杭州市公安机关监控与警情处置标准操作程序", "杭州市公安机关处置扬言自杀警情标准操作程序", "杭州市公安机关警用车辆管理规范",
            "杭州市公安机关设卡盘查规范(试行)", "杭州市公安机关涉爆现场处置标准操作程序", "杭州市公安机关搜查标准操作程序",
            "杭州市公安机关吸毒现场检测标准操作程序", "杭州市公安机关刑事辨认工作操作规范", "杭州市公安机关押送标准操作程序",
            "杭州市公安机关抓捕嫌疑人标准操作程序"};

    public static String[] fileNameEN = {"rite", "media", "dead", "help", "drowning",
            "coin", "tipsy", "activity", "control", "suicide", "policecar",
            "card", "explosion", "search", "druggy", "criminal", "escort",
            "arrest"};

    public static Integer[] icon = {R.drawable.rite, R.drawable.media, R.drawable.dead,
            R.drawable.help, R.drawable.drowning, R.drawable.coin,
            R.drawable.tipsy, R.drawable.activity, R.drawable.control,
            R.drawable.suicide, R.drawable.policecar, R.drawable.card,
            R.drawable.explosion, R.drawable.search, R.drawable.druggy,
            R.drawable.criminal, R.drawable.escort, R.drawable.arrest};
}
