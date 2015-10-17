package bookmgr.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

/**
 * A model for creating objects from rows of table "books"
 */
@Table("books")
public class Book extends Model {
}

