package com.stx.core.linklist;

/**
 * @author tongxiang.stx
 * @date 2019/07/15
 */
public class NodeTest {
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }

        Node(E element) {
            this.item = element;
        }

        public void setItem(E item) {
            this.item = item;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }
    }

    public static Node getList(int begin, int num){
        Node<Integer> head = new Node(begin), next = null, prev = null, cur = null;
        int i = begin;
        cur = head;
        while (i++ < num) {
            next = new Node(i);
            cur.setNext(next);
            cur.setPrev(prev);

            prev = cur;
            cur = next;
            next = next.next;
        }

        cur = head;
        //while (cur != null) {
        //    System.out.println(cur.item);
        //    cur = cur.next;
        //}

        return head;
    }

    public static void main(String[]args){
        Node list = getList(1, 10);
    }

}
