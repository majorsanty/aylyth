package moriyashiine.aylyth.common.entity.ai.sensor;

import com.google.common.collect.ImmutableSet;
import moriyashiine.aylyth.common.entity.mob.TameableHostileEntity;
import moriyashiine.aylyth.common.registry.ModMemoryTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;

import java.util.List;
import java.util.Set;

public class OwningPlayerSensor<E extends LivingEntity & TameableHostileEntity> extends Sensor<E> {

    @Override
    public Set<MemoryModuleType<?>> getOutputMemoryModules() {
        return ImmutableSet.of(
                MemoryModuleType.NEAREST_PLAYERS,
                ModMemoryTypes.OWNER_PLAYER
        );
    }

    @Override
    protected void sense(ServerWorld world, E entity) {
        if (!entity.getBrain().hasMemoryModule(MemoryModuleType.NEAREST_PLAYERS) || !(entity.getOwner() instanceof PlayerEntity player)) {
            return;
        }
        List<PlayerEntity> players = entity.getBrain().getOptionalMemory(MemoryModuleType.NEAREST_PLAYERS).get();
        if (players.contains(player)) {
            Brain<?> brain = entity.getBrain();
            brain.remember(ModMemoryTypes.OWNER_PLAYER, player);
        }
    }
}
