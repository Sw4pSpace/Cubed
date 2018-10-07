package net.glowstone.io.persistence;

import com.google.inject.Inject;

public class WhiteListImpl extends UUIDListImpl implements WhiteList{

    @Inject
    public WhiteListImpl(PersistenceManager persistenceManager) {
        super(persistenceManager);
        setTablename(Table.WHITELIST.getName());
    }
}
