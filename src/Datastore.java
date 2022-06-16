interface Datastore<T> {
    T Retrieve();
    void Save(T type);
}
