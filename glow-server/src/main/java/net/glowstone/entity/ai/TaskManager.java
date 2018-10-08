package net.glowstone.entity.ai;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import net.glowstone.entity.GlowLivingEntity;

public class TaskManager {

    private final GlowLivingEntity entity;
    private final Map<AITask, EntityTask> tasksByName = new ConcurrentHashMap<>();
    private final ClassToInstanceMap<EntityTask> tasksByClass = MutableClassToInstanceMap.create(
            new ConcurrentHashMap<>());
    private final Set<EntityTask> tasks = new ConcurrentSkipListSet<>();

    public TaskManager(GlowLivingEntity entity) {
        this.entity = entity;
    }

    /**
     * Returns the existing task with a given name.
     *
     * @param name the name to look up
     * @return the task with that name, or null if no registered task matches
     */
    public EntityTask getTask(AITask name) {
        return tasksByName.get(name);
    }

    /**
     * Returns the existing task whose class is exactly a given class (and not a subclass -- this
     * will always return null for an abstract type).
     *
     * @param clazz the class to look up
     * @return one of this manager's tasks that's an instance of that class and not a subclass, or
     *         null if no such tasks are registered
     */
    public EntityTask getTask(Class<? extends EntityTask> clazz) {
        return tasksByClass.get(clazz);
    }

    /**
     * Returns a new instance of the task with a given name.
     *
     * @param name the task name to look up
     * @return a task with the given name, or null if none match or the matching task class doesn't
     *         have a parameterless constructor
     */
    public EntityTask getNewTask(AITask name) {
        // TODO: Refactor to use a constructor-reference Supplier<? extends EntityTask>
        Class<? extends EntityTask> clazz = EntityDirector.getEntityTask(name);
        try {
            if (clazz != null) {
                return clazz.newInstance();
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Rebuild the list of tasks according to {@link EntityDirector#getEntityMobStateTask
     * (EntityType, MobState)}.
     */
    public void updateState() {
        cancelTasks();
        for (AITask task : EntityDirector
                .getEntityMobStateTask(entity.getType(), entity.getState())) {
            addTask(task);
        }
    }

    /**
     * Cancels and unregisters the given task.
     * @param task the task to cancel
     */
    public void cancel(EntityTask task) {
        task.reset(entity);
        tasksByName.remove(task.getName(), task);
        tasksByClass.remove(task.getClass(), task);
        tasks.remove(task);
    }

    /**
     * Cancels and unregisters all tasks.
     */
    public void cancelTasks() {
        tasks.forEach(task -> task.reset(entity));
        tasksByName.clear();
        tasksByClass.clear();
        tasks.clear();
    }

    public void pulse() {
        tasks.forEach(task -> task.pulse(entity));
    }

    /**
     * Add the given task, replacing any existing task with the same name.
     *
     * @param task the task to add
     */
    public void addTask(EntityTask task) {
        if (task != null) {
            AITask name = task.getName();
            EntityTask oldTask = getTask(name);
            if (oldTask != null) {
                cancel(oldTask);
            }
            tasksByName.put(name, task);
            tasksByClass.put(task.getClass(), task);
            tasks.add(task);
        }
    }

    /**
     * Add the task with this name, or replace it with a new instance if it already exists.
     *
     * @param taskName the task name
     */
    public void addTask(AITask taskName) {
        EntityTask oldTask = getTask(taskName);
        if (oldTask != null) {
            cancel(oldTask);
        }
        EntityTask newTask = getNewTask(taskName);
        tasksByName.put(taskName, newTask);
        tasksByClass.put(newTask.getClass(), newTask);
        tasks.add(newTask);
    }
}
