//package com.myself.util;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * java 树形结构遍历
// *
// * @author Created by zion
// * @Date 2018/3/9.
// * /**
// * 深度优先遍历树形结构
// * @return [
// * {
// * "id":"",
// * "name":"",
// * "type":"",
// * "child":[
// * {
// * "id":"",
// * "name":"",
// * "type":"",
// * "child":[
// * {
// * "id":"",
// * "name":"",
// * "type":"",
// * "child":[.没有穷尽.]
// * },{},{}
// * ]
// * },{},{}
// * ]
// * },{},{}
// * ]
// */
public class TreeUtil {
//
//    private final static Logger logger = LoggerFactory.getLogger(TreeUtil.class);
//
//    /**
//     * 输入参数
//     */
//    List<Node> nodes = new ArrayList<>();
//
//    public TreeUtil() {
//    }
//
//    public TreeUtil(List<Node> nodes) {
//        super();
//        this.nodes = nodes;
//    }
//
//    /**
//     * 构建树形结构入口方法
//     *
//     * @param nodes 输入参数
//     * @return jsonString
//     */
//    public String buildTree(List<Node> nodes) {
//        //类实例初始化，初始化输入参数
//        TreeUtil treeBuilder = new TreeUtil(nodes);
//        //真正构建树形结构方法入口
//        return treeBuilder.buildJSONTree();
//    }
//
//    /**
//     * 树形结构转换为String类型
//     *
//     * @return
//     */
//    public String buildJSONTree() {
//        //真正构建树形结构方法入口
//        List<Node> nodeTree = buildTree();
//        //树形结构转换为JsonArray
//        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(nodeTree));
//        //返回JsonString
//        return jsonArray.toString();
//    }
//
//    /**
//     * 构建树形结构
//     *
//     * @return
//     */
//    public List<Node> buildTree() {
//        //当前方法返回参数
//        List<Node> treeNodes = new ArrayList<>();
//        //获取所有的根节点（根节点可能有多个）
//        List<Node> rootNodes = getRootNodes();
//        //获取所有根节点下的子节点（递归）
//        for (Node rootNode : rootNodes) {
//            buildChildNodes(rootNode);
//            treeNodes.add(rootNode);
//        }
//        return treeNodes;
//    }
//
//
//    /**
//     * 递归子节点
//     *
//     * @param node
//     */
//    public void buildChildNodes(Node node) {
//        List<Node> children = getChildNodes(node);
//        if (!children.isEmpty()) {
//            for (Node child : children) {
//                buildChildNodes(child);
//            }
//            node.setChildren(children);
//        }
//    }
//
//    /**
//     * 获取父节点下所有的子节点
//     *
//     * @param pnode
//     * @return
//     */
//    public List<Node> getChildNodes(Node pnode) {
//        List<Node> childNodes = new ArrayList<>();
//        for (Node n : nodes) {
//            if (pnode.getId().equals(n.getPid())) {
//                childNodes.add(n);
//            }
//        }
//        return childNodes;
//    }
//
//    /**
//     * 获取集合中所有的根节点
//     * 不需要判断根节点是什么标志“root”还是“/”
//     * 只要当前节点没有子节点就证明当前节点是根节点
//     *
//     * @return
//     */
//    public List<Node> getRootNodes() {
//        List<Node> rootNodes = new ArrayList<>();
//        for (Node n : nodes) {
//            if (rootNode(n)) {
//                rootNodes.add(n);
//            }
//        }
//        return rootNodes;
//    }
//
//    /**
//     * 判断是否为根节点
//     *
//     * @param node
//     * @return
//     */
//    public boolean rootNode(Node node) {
//        boolean isRootNode = true;
//        for (Node n : nodes) {
//            if (node.getPid().equals(n.getId())) {
//                isRootNode = false;
//                break;
//            }
//        }
//        return isRootNode;
//    }
//
//    /**
//     * 静态内部类
//     */
//    public static class Node {
//        private String id;
//        private String pid;
//        private String name;
//        private List<Node> children;
//
//        public Node() {
//        }
//
//        public Node(String id, String pid, String name) {
//            super();
//            this.id = id;
//            this.pid = pid;
//            this.name = name;
//        }
//
//        public Node(String id, String pid, String name, List<Node> children) {
//            this.id = id;
//            this.pid = pid;
//            this.name = name;
//            this.children = children;
//        }
//
//        public String getId() {
//            return id;
//        }
//
//        public void setId(String id) {
//            this.id = id;
//        }
//
//        public String getPid() {
//            return pid;
//        }
//
//        public void setPid(String pid) {
//            this.pid = pid;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public List<Node> getChildren() {
//            return children;
//        }
//
//        public void setChildren(List<Node> children) {
//            this.children = children;
//        }
//    }
//
//    public static void main(String[] args) {
//        //
////        List<String>
//        // 拼装树形json字符串
//        List<Node> nodes = new ArrayList<>();
//        nodes.add(new Node("1", "-1", "商品目录"));
//        nodes.add(new Node("11", "1", "日用品"));
//        nodes.add(new Node("12", "1", "食品"));
//        nodes.add(new Node("111", "11", "洗发水"));
//        nodes.add(new Node("11211", "12", "面包"));
//        nodes.add(new Node("1111", "111", "霸王"));
//        String json = new TreeUtil().buildTree(nodes);
//        System.out.println(json);
//    }
}
