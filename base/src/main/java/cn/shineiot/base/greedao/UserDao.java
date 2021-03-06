package cn.shineiot.base.greedao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import cn.shineiot.base.bean.User;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER".
*/
public class UserDao extends AbstractDao<User, Long> {

    public static final String TABLENAME = "USER";

    /**
     * Properties of entity User.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property Admin = new Property(1, boolean.class, "admin", false, "ADMIN");
        public final static Property Email = new Property(2, String.class, "email", false, "EMAIL");
        public final static Property Icon = new Property(3, String.class, "icon", false, "ICON");
        public final static Property Nickname = new Property(4, String.class, "nickname", false, "NICKNAME");
        public final static Property Password = new Property(5, String.class, "password", false, "PASSWORD");
        public final static Property PublicName = new Property(6, String.class, "publicName", false, "PUBLIC_NAME");
        public final static Property Token = new Property(7, String.class, "token", false, "TOKEN");
        public final static Property Type = new Property(8, int.class, "type", false, "TYPE");
        public final static Property Username = new Property(9, String.class, "username", false, "USERNAME");
    }


    public UserDao(DaoConfig config) {
        super(config);
    }
    
    public UserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + // 0: id
                "\"ADMIN\" INTEGER NOT NULL ," + // 1: admin
                "\"EMAIL\" TEXT," + // 2: email
                "\"ICON\" TEXT," + // 3: icon
                "\"NICKNAME\" TEXT," + // 4: nickname
                "\"PASSWORD\" TEXT," + // 5: password
                "\"PUBLIC_NAME\" TEXT," + // 6: publicName
                "\"TOKEN\" TEXT," + // 7: token
                "\"TYPE\" INTEGER NOT NULL ," + // 8: type
                "\"USERNAME\" TEXT);"); // 9: username
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, User entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindLong(2, entity.getAdmin() ? 1L: 0L);
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(3, email);
        }
 
        String icon = entity.getIcon();
        if (icon != null) {
            stmt.bindString(4, icon);
        }
 
        String nickname = entity.getNickname();
        if (nickname != null) {
            stmt.bindString(5, nickname);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(6, password);
        }
 
        String publicName = entity.getPublicName();
        if (publicName != null) {
            stmt.bindString(7, publicName);
        }
 
        String token = entity.getToken();
        if (token != null) {
            stmt.bindString(8, token);
        }
        stmt.bindLong(9, entity.getType());
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(10, username);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, User entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindLong(2, entity.getAdmin() ? 1L: 0L);
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(3, email);
        }
 
        String icon = entity.getIcon();
        if (icon != null) {
            stmt.bindString(4, icon);
        }
 
        String nickname = entity.getNickname();
        if (nickname != null) {
            stmt.bindString(5, nickname);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(6, password);
        }
 
        String publicName = entity.getPublicName();
        if (publicName != null) {
            stmt.bindString(7, publicName);
        }
 
        String token = entity.getToken();
        if (token != null) {
            stmt.bindString(8, token);
        }
        stmt.bindLong(9, entity.getType());
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(10, username);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public User readEntity(Cursor cursor, int offset) {
        User entity = new User( //
            cursor.getLong(offset + 0), // id
            cursor.getShort(offset + 1) != 0, // admin
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // email
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // icon
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // nickname
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // password
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // publicName
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // token
            cursor.getInt(offset + 8), // type
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9) // username
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, User entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setAdmin(cursor.getShort(offset + 1) != 0);
        entity.setEmail(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setIcon(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setNickname(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setPassword(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setPublicName(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setToken(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setType(cursor.getInt(offset + 8));
        entity.setUsername(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(User entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(User entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(User entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
