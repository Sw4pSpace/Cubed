package net.glowstone.io.persistence;

import com.google.inject.Inject;

public class OpsListImpl extends UUIDListImpl implements OpsList {

    @Inject
    public OpsListImpl(PersistenceManager persistenceManager) {
        super(persistenceManager);
        setTablename(Table.OPS.getName());
    }
}
