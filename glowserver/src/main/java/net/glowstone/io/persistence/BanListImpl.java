package net.glowstone.io.persistence;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import net.glowstone.util.bans.GlowBanEntry;
import org.bukkit.BanEntry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Set;

public class BanListImpl implements BanList{

    private PersistenceManager persistenceManager;

    @Inject
    public BanListImpl(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    private GlowBanEntry getBanEntry(ResultSet set) throws SQLException {
        return new GlowBanEntry(null,
                set.getString("target"),
                set.getString("reason"),
                set.getDate("created"),
                set.getDate("expires"),
                set.getString("source")
        );
    }

    @Override
    public void purgeBans() {
        String query = "DELETE FROM ? WHERE expires <= \'?\';";
        persistenceManager.executeUpdate(query, Lists.newArrayList(Table.BANNED.getName(), new Date()));
    }

    @Override
    public BanEntry getBanEntry(String target) {

        // TODO When refactoring code, make our own BanEntry interface/impl for this

        purgeBans();
        String query = "SELECT * FROM ? WHERE target = \'?\';";
        DatabaseResult<ResultSet> databaseResult = persistenceManager.executeQuery(query, Lists.newArrayList(Table.BANNED.getName(), target));
        ResultSet result = databaseResult.getResults();
        GlowBanEntry banEntry = null;

        try {
            if(result.next()) {
                banEntry = getBanEntry(result);
            }
            databaseResult.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return banEntry;
    }

    @Override
    public void addBan(String target, String reason, Date expires, String source) {
        String query = "INSERT INTO " + Table.BANNED.getName() + " (target, reason, created, expires, source) VALUES (?, ?, ?, ?, ?);";
        DatabaseStatement databaseStatement = persistenceManager.execute(query);
        try {
            databaseStatement.getStatement().setString(1, target);
            databaseStatement.getStatement().setString(2, reason);
            databaseStatement.getStatement().setDate(3, new java.sql.Date(new Date().getTime()));
            databaseStatement.getStatement().setDate(4, expires != null ? new java.sql.Date(expires.getTime()) : null);
            databaseStatement.getStatement().setString(5, source);
            if(databaseStatement.getStatement().executeUpdate() != 1) {
                // TODO log that there was an error saving
            }
            databaseStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<BanEntry> getBanEntries() {
        purgeBans();
        String query = "SELECT * FROM ?;";
        DatabaseResult<ResultSet> databaseResult = persistenceManager.executeQuery(query, Lists.newArrayList(Table.BANNED.getName()));
        ResultSet result = databaseResult.getResults();
        Set<BanEntry> banEntries = Sets.newHashSet();

        try {
            while(result.next()) {
                banEntries.add(getBanEntry(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return banEntries;
    }

    @Override
    public boolean isBanned(String target) {
        purgeBans();
        String query = "SELECT * FROM ? WHERE target = \'?\';";
        DatabaseResult<ResultSet> databaseResult = persistenceManager.executeQuery(query, Lists.newArrayList(Table.BANNED.getName(), target));
        ResultSet result = databaseResult.getResults();
        boolean flag = false;

        try {
            if(result.next()){
                flag = true;
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public void pardon(String target) {
        String query = "DELETE FROM ? WHERE target = \'?\';";
        DatabaseResult<Integer> databaseResult = persistenceManager.executeUpdate(query, Lists.newArrayList(Table.BANNED.getName(), target));

        if(databaseResult.getResults() != 1) {
            // TODO log that there was an error pardoning target
        }

        try {
            databaseResult.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
