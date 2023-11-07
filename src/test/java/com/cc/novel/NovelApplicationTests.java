package com.cc.novel;

import com.cc.service.TitleService;
import com.cc.service.imp.TitleServiceImpl;
import com.cc.util.FileUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NovelApplicationTests {
	@Resource
	TitleServiceImpl titleService;
	@Test
	void contextLoads() {
		titleService.chapterNameToNext("D:/novel/2",10,1646);
	}

}
