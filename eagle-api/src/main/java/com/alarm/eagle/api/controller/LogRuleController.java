package com.alarm.eagle.api.controller;

import com.alarm.eagle.api.bean.LogRule;
import com.alarm.eagle.api.domain.LogRuleDo;
import com.alarm.eagle.api.service.LogRuleService;
import com.alarm.eagle.response.Response;
import com.alarm.eagle.response.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by luxiaoxun on 2020/01/29.
 */
@RestController
@RequestMapping("/log")
public class LogRuleController {
    private static final Logger logger = LoggerFactory.getLogger(LogRuleController.class);

    @Resource
    private LogRuleService logRuleService;

    @PostMapping("/trace")
    public Response receive(@RequestParam String data) {
        logger.info("data={}", data);
        return ResponseUtil.success();
    }

    @GetMapping("/rules")
    public Response getRules() {
        List<LogRule> logRuleList = logRuleService.queryAllRules();
        return ResponseUtil.success(logRuleList);
    }

    @GetMapping("/rules")
    public Response getRuleById(@RequestParam int id) {
        LogRule logRule = logRuleService.queryRuleById(id);
        if (logRule != null) {
            return ResponseUtil.success(logRule);
        } else {
            return ResponseUtil.fail(404, "没找到对应的规则");
        }
    }

    @PostMapping("/rules")
    public Response saveOrUpdateRules(@RequestBody LogRuleDo logRuleDo) {
        logger.info("logRuleDo={}", logRuleDo);
        LogRuleDo ret = logRuleService.saveOrUpdateRule(logRuleDo);
        return ResponseUtil.success(ret);
    }

    @DeleteMapping("/rules/{id}")
    public Response deleteRule(@PathVariable("id") int id) {
        logger.info("deleteRuleById={}", id);
        boolean ret = logRuleService.deleteRuleById(id);
        if (ret) {
            return ResponseUtil.success(id);
        } else {
            return ResponseUtil.fail(500, "删除规则失败");
        }
    }

}
