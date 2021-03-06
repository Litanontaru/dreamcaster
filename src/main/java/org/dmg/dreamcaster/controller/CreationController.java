package org.dmg.dreamcaster.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import lombok.RequiredArgsConstructor;
import org.dmg.dreamcaster.model.Activation;
import org.dmg.dreamcaster.model.CreationStart;
import org.dmg.dreamcaster.model.Effect;
import org.dmg.dreamcaster.model.Param;
import org.dmg.dreamcaster.model.ParamList;
import org.dmg.dreamcaster.model.Power;
import org.dmg.dreamcaster.service.ActivationLocator;
import org.dmg.dreamcaster.service.EffectLocator;
import org.dmg.dreamcaster.service.PowerManager;
import org.dmg.dreamcaster.service.PowerStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreationController {
    private final EffectLocator effectLocator;
    private final ActivationLocator activationLocator;
    private final PowerManager powerManager;
    private final PowerStore powerStore;

    @RequestMapping("/powers/simple/add")
    public HttpEntity<CreationStart> addSimplePower() {
        CreationStart start = new CreationStart();
        for (String key : effectLocator.effects()) {
            start.add(linkTo(methodOn(CreationController.class).viewEffect(key)).withRel(key));
        }
        return new ResponseEntity<>(start, HttpStatus.OK);
    }

    @RequestMapping("/powers/simple/effect/{key}")
    public HttpEntity<Effect> viewEffect(@PathVariable("key") String key) {
        Effect effect = effectLocator.create(key);
        effect.add(linkTo(methodOn(CreationController.class).newPower(key)).withRel("Создать Силу с этим эффектом"));

        return new ResponseEntity<>(effect, HttpStatus.OK);
    }

    @RequestMapping("/powers/simple/effect/{key}/add")
    public HttpEntity<Power> newPower(@PathVariable("key") String key) {

        Power power = new Power();
        power.setKey(key);
        Effect effect = effectLocator.create(key);

        power.setEffect(effect);
        powerManager.rate(power);
        powerStore.save(power);

        power.add(linkTo(methodOn(CreationController.class).viewActivationsToAddToPower(power.getId())).withRel("Добавить активацию"));
        power.add(linkTo(methodOn(CreationController.class).viewParams(power.getId())).withRel("Параметры"));
        power.add(linkTo(methodOn(CreationController.class).viewEffectParams(power.getId())).withRel("Параметры Эффекта"));

        return new ResponseEntity<>(power, HttpStatus.OK);
    }

    @RequestMapping("/powers/{power_id}")
    public HttpEntity<Power> viewPower(@PathVariable(value = "power_id") Long power_id) {
        Power power = powerStore.get(power_id);
        return new ResponseEntity<>(power, HttpStatus.OK);
    }

    @RequestMapping("/powers/{power_id}/activations/add")
    public HttpEntity<CreationStart> viewActivationsToAddToPower(@PathVariable(value = "power_id") Long power_id) {
        CreationStart start = new CreationStart();
        for (String key : activationLocator.activations()) {
            start.add(linkTo(methodOn(CreationController.class).viewAddingActivationToPower(power_id, key)).withRel(key));
        }
        start.add(linkTo(methodOn(CreationController.class).viewPower(power_id)).withRel("Назад"));
        return new ResponseEntity<>(start, HttpStatus.OK);
    }

    @RequestMapping("/powers/{power_id}/activations/view/{key}")
    public HttpEntity<Activation> viewAddingActivationToPower(
            @PathVariable(value = "power_id") Long power_id,
            @PathVariable(value = "key") String key
    ) {
        Activation activation = activationLocator.create(key);
        activation.add(linkTo(methodOn(CreationController.class).addActivationToPower(power_id, key)).withRel("Добавить активацию"));
        activation.add(linkTo(methodOn(CreationController.class).viewActivationsToAddToPower(power_id)).withRel("Назад"));

        return new ResponseEntity<>(activation, HttpStatus.OK);
    }

    @RequestMapping("/powers/{power_id}/activations/add/{key}")
    public HttpEntity<Power> addActivationToPower(
            @PathVariable(value = "power_id") Long power_id,
            @PathVariable(value = "key") String key
    ) {
        Power power = powerStore.get(power_id);
        List<Activation> activations = power.getActivations();
        if (activations == null) {
            activations = new ArrayList<>();
            power.setActivations(activations);
        }
        Activation activation = activationLocator.create(key);
        activation.add(linkTo(methodOn(CreationController.class).removeActivation(power_id, activations.size())).withRel("Удалить активацию"));

        activations.add(activation);
        powerManager.rate(power);
        powerStore.save(power);

        return new ResponseEntity<>(power, HttpStatus.OK);
    }

    @RequestMapping("/powers/{power_id}/activations/{index}/remove")
    public HttpEntity<Power> removeActivation(
            @PathVariable(value = "power_id") Long power_id,
            @PathVariable(value = "index") Integer index
    ) {
        Power power = powerStore.get(power_id);
        List<Activation> activations = power.getActivations();
        activations.remove(index.intValue());

        for (int i = 0; i < activations.size(); i++) {
            Activation activation = activations.get(i);
            activation.removeLinks();
            activation.add(linkTo(methodOn(CreationController.class).removeActivation(power_id, i)).withRel("Удалить активацию"));
        }

        powerManager.rate(power);
        powerStore.save(power);

        return new ResponseEntity<>(power, HttpStatus.OK);
    }

    @RequestMapping("/powers/{power_id}/params")
    public HttpEntity<ParamList> viewParams(
        @PathVariable(value = "power_id") Long power_id
    ) {
        Power power = powerStore.get(power_id);
        List<Param> params = power.getParams().entrySet().stream()
                .map(e -> new Param(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
        for (Param param : params) {
            if (param.getValue() instanceof Integer) {
                param.add(linkTo(methodOn(CreationController.class).increaseParam(power_id, param.getName())).withRel("Увеличить"));
                param.add(linkTo(methodOn(CreationController.class).decreaseParam(power_id, param.getName())).withRel("Уменьшить"));
            }
        }

        ParamList paramList = new ParamList(params);
        paramList.add(linkTo(methodOn(CreationController.class).viewPower(power_id)).withRel("Назад"));
        return new ResponseEntity<>(paramList, HttpStatus.OK);
    }

    @RequestMapping("/powers/{power_id}/params/{name}/increase")
    public HttpEntity<Power> increaseParam(@PathVariable("power_id") Long power_id, @PathVariable("name") String name) {
        Power power = powerStore.get(power_id);
        Integer v = (Integer) power.getParams().get(name);
        power.getParams().put(name, v + 1);

        powerManager.rate(power);
        powerStore.save(power);

        return new ResponseEntity<>(power, HttpStatus.OK);
    }

    @RequestMapping("/powers/{power_id}/params/{name}/decrease")
    public HttpEntity<Power> decreaseParam(@PathVariable("power_id") Long power_id, @PathVariable("name") String name) {
        Power power = powerStore.get(power_id);
        Integer v = (Integer) power.getParams().get(name);
        power.getParams().put(name, v - 1);

        powerManager.rate(power);
        powerStore.save(power);

        return new ResponseEntity<>(power, HttpStatus.OK);
    }

    @RequestMapping("/powers/{power_id}/effect/params")
    public HttpEntity<ParamList> viewEffectParams(
            @PathVariable(value = "power_id") Long power_id
    ) {
        Power power = powerStore.get(power_id);
        List<Param> params = power.getEffect().getParams().entrySet().stream()
                .map(e -> new Param(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
        for (Param param : params) {
            if (param.getValue() instanceof Integer) {
                param.add(linkTo(methodOn(CreationController.class).increaseEffectParam(power_id, param.getName())).withRel("Увеличить"));
                param.add(linkTo(methodOn(CreationController.class).decreaseEffectParam(power_id, param.getName())).withRel("Уменьшить"));
            }
        }

        ParamList paramList = new ParamList(params);
        paramList.add(linkTo(methodOn(CreationController.class).viewPower(power_id)).withRel("Назад"));
        return new ResponseEntity<>(paramList, HttpStatus.OK);
    }

    @RequestMapping("/powers/{power_id}/effect/params/{name}/increase")
    public HttpEntity<Power> increaseEffectParam(@PathVariable("power_id") Long power_id, @PathVariable("name") String name) {
        Power power = powerStore.get(power_id);
        Integer v = (Integer) power.getEffect().getParams().get(name);
        power.getEffect().getParams().put(name, v + 1);

        powerManager.rate(power);
        powerStore.save(power);

        return new ResponseEntity<>(power, HttpStatus.OK);
    }

    @RequestMapping("/powers/{power_id}/effect/params/{name}/decrease")
    public HttpEntity<Power> decreaseEffectParam(@PathVariable("power_id") Long power_id, @PathVariable("name") String name) {
        Power power = powerStore.get(power_id);
        Integer v = (Integer) power.getEffect().getParams().get(name);
        power.getEffect().getParams().put(name, v - 1);

        powerManager.rate(power);
        powerStore.save(power);

        return new ResponseEntity<>(power, HttpStatus.OK);
    }
}