package com.ipc1.proyecto2.models;

/**
 *
 * @author Admin
 */

public class NodeShoppingRecord {

    private ShoppingRecord  record;
    private NodeShoppingRecord next;

    public NodeShoppingRecord(ShoppingRecord record) {
        this.record    = record;
        this.next = null;
    }

    public ShoppingRecord getRecord() {
        return record;
    }

    public void setRecord(ShoppingRecord record) {
        this.record = record;
    }

    public NodeShoppingRecord getSiguiente() {
        return next;
    }

    public void setSiguiente(NodeShoppingRecord next) {
        this.next = next;
    } 
}
