package org.exmple.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestJson {

    @Test
    @SuppressWarnings("unchecked")
    public void testJsonRemove() {
        String str = "{\"arr\":[{\"code\":1,\"value\":\"111\"},{\"code\":2,\"value\":\"111\"},{\"code\":3,\"value\":\"111\"}]}";
        JSONObject input = JSON.parseObject(str);

        Object eval = JSONPath.eval(input, "arr[code=1][0].value");
        List array;
        if (eval instanceof List) {
            array = ((List) eval);
        } else {
            array = new ArrayList<>();
            array.add(eval);
        }

        removeNode(input, array);
        System.out.println(input);
    }

    private void removeNode(JSON root, List<?> nodes) {
        if (root instanceof JSONObject) {
            JSONObject object = (JSONObject) root;
            object.forEach((nodeName, node) -> {
                if (pointContains(nodes, node)) {
                    object.remove(nodeName);
                } else {
                    Object subNode = object.get(nodeName);
                    if (subNode instanceof JSON) {
                        removeNode((JSON) subNode, nodes);
                    }
                }
            });
        }

        if (root instanceof JSONArray) {
            JSONArray array = (JSONArray) root;
            for (int i = array.size() - 1; i >= 0; i--) {
                Object object = array.get(i);
                if (pointContains(nodes, object)) {
                    array.remove(i);
                } else {
                    if (object instanceof JSON) {
                        removeNode(((JSON) object), nodes);
                    }
                }
            }
        }
    }

    private boolean pointContains(List<?> nodes, Object node) {
        for (Object item : nodes) {
            if (item == node) {
                return true;
            }
        }
        return false;
    }
}
