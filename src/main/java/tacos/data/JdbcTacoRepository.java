package tacos.data;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import tacos.Ingredient;
import tacos.Taco;

@Repository
public class JdbcTacoRepository implements TacoRepository {

    private JdbcTemplate jdbc;

    public JdbcTacoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Taco save(Taco taco) {
        long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);
        for (Ingredient ingredient : taco.getIngredients()) {
            saveIngredientToTaco(ingredient, tacoId);
        }

        return taco;
    }

    private long saveTacoInfo(Taco taco) {
        taco.setCreatedAt(new Date());

        String sql = "insert into Taco (name, createdAt) values (?, ?)";
        PreparedStatementCreatorFactory pscf;
        pscf = new PreparedStatementCreatorFactory(sql, Types.VARCHAR, Types.TIMESTAMP);

        Timestamp createAt = new Timestamp(taco.getCreatedAt().getTime());

        List<Object> params = Arrays.asList(taco.getName(), createAt);

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(params);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);

        return keyHolder.getKey().longValue();
    }

    private void saveIngredientToTaco(Ingredient ingredient, long tacoId) {
        String sql = "insert into Taco_Ingredients (taco, ingredient) values (?, ?)";
        jdbc.update(sql, tacoId, ingredient.getId());
    }

}
