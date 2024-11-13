package Skills.Warrior;

import Skills.SkillType;
import Skills.Skill;

public class PowerStrike extends Skill {

    private static final String name = "Power Strike";
    private static final String description = "High single-target damage";
    private static final int cost = 20;
    private static final double damage = 1.5; // 150% of attack power
    private static final SkillType SKILL_TYPE = SkillType.PHYSICAL;

    public PowerStrike() {
        super(name, description, cost, damage, SKILL_TYPE);
    }

    @Override
    public void useSkill() {

    }
}
