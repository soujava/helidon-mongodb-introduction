package expert.os.samples.helidon.mongodb;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;

@Entity
public record Tool (@Id String sku, @Column String name, @Column String type, @Column int quantity) {

}
