package com.ipc1.proyecto2.models;

/**
 *
 * @author Admin
 */

public class NodeShoppingRecord {

    private ShoppingRecord  record;
    private NodeShoppingRecord siguiente;

    public NodeShoppingRecord(ShoppingRecord record) {
        this.record    = record;
        this.siguiente = null;
    }

    public ShoppingRecord getRecord() {
        return record;
    }

    public void setRecord(ShoppingRecord record) {
        this.record = record;
    }

    public NodeShoppingRecord getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodeShoppingRecord siguiente) {
        this.siguiente = siguiente;
    } 
}
