package com.github.dariozubaray.springboot.repository;

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
import com.github.dariozubaray.springboot.models.Ingredient;
import com.github.dariozubaray.springboot.models.Taco;

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
        Long tacoTime = taco.getCreatedAt().getTime();
        List<Object> tacoParams = Arrays.asList(taco.getName(), new Timestamp(tacoTime));
        PreparedStatementCreator psc = pscf .newPreparedStatementCreator(tacoParams);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);
        return keyHolder.getKey().longValue();
    }

    private void saveIngredientToTaco(Ingredient ingredient, long tacoId) {
        jdbc.update("insert into Taco_Ingredients (taco, ingredient) "
                    + "values (?, ?)",
                    tacoId,
                    ingredient.getId());
    }
}
