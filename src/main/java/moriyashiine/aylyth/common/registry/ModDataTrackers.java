package moriyashiine.aylyth.common.registry;

import moriyashiine.aylyth.common.entity.ai.BasicAttackType;
import moriyashiine.aylyth.common.entity.mob.TulpaEntity;
import moriyashiine.aylyth.common.entity.mob.WreathedHindEntity;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.util.Arm;

public class ModDataTrackers {
    public static final TrackedDataHandler<TulpaEntity.ActionState> TULPA_ACTION_STATE = register(TrackedDataHandler.ofEnum(TulpaEntity.ActionState.class));
    public static final TrackedDataHandler<WreathedHindEntity.AttackType> WREATHED_HIND_ATTACKS = register(TrackedDataHandler.ofEnum(WreathedHindEntity.AttackType.class));
    public static final TrackedDataHandler<BasicAttackType> BASIC_ATTACK = register(TrackedDataHandler.ofEnum(BasicAttackType.class));
    public static final TrackedDataHandler<Arm> PRIMARY_HAND = register(TrackedDataHandler.ofEnum(Arm.class));

    private static <T> TrackedDataHandler<T> register(TrackedDataHandler<T> dataHandler) {
        TrackedDataHandlerRegistry.register(dataHandler);
        return dataHandler;
    }

    public static void init() {}
}
